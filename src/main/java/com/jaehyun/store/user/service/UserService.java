package com.jaehyun.store.user.service;

import com.jaehyun.store.global.config.JwtTokenProvider;
import com.jaehyun.store.user.domain.entity.User;
import com.jaehyun.store.user.domain.dto.UserCreateDto;
import com.jaehyun.store.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    public Long join(UserCreateDto userCreateDto) {
        User user = User.builder()
                .userPhoneNum(userCreateDto.getUserPhoneNum())
                .userPassword(passwordEncoder.encode(userCreateDto.getUserPassword()))
                .roles(Collections.singletonList("USER"))
                .build();
        return userRepository.save(user).getUserId();
    }

    //회원 삭제
    public boolean deleteUser(String userPhoneNum, String password) {
        Optional<User> optionalUser = userRepository.findByUserPhoneNum(userPhoneNum);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getUserPassword())) {
                userRepository.delete(user);
                return true;
            }
        }
        return false;
    }

    //로그인
    public String login(UserCreateDto userCreateDto) {
        User user = userRepository.findByUserPhoneNum(userCreateDto.getUserPhoneNum())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 userPhoneNum 입니다."));
        if (!passwordEncoder.matches(userCreateDto.getUserPassword(), user.getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUserPhoneNum(), user.getRoles());
    }
}