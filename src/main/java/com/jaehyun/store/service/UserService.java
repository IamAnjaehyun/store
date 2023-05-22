package com.jaehyun.store.service;

import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.User;
import com.jaehyun.store.model.dto.UserDto;
import com.jaehyun.store.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long join(UserDto userDto) {
        User user = User.builder()
                .userPhoneNum(userDto.getUserPhoneNum())
                .userPassword(passwordEncoder.encode(userDto.getUserPassword()))
                .roles(Collections.singletonList("USER"))
                .build();
        return userRepository.save(user).getUserId();
    }

    public String login(UserDto userDto) {
        User user = userRepository.findByUserPhoneNum(userDto.getUserPhoneNum())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 userPhoneNum 입니다."));
        if (!passwordEncoder.matches(userDto.getUserPassword(), user.getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUserPhoneNum(), user.getRoles());
    }
}
