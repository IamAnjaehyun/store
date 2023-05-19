package com.jaehyun.store.controller.user;

import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.repository.UserRepository;
import com.jaehyun.store.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user) {
        return userRepository.save(User.builder()
                .userPhoneNum(user.get("userPhoneNum"))
                .userPassword(passwordEncoder.encode(user.get("userPassword")))
                .roles(Collections.singletonList("USER")) // 최초 가입시 USER 로 설정
                .build()).getUserId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByUserPhoneNum(user.get("userPhoneNum"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 userPhoneNum 입니다."));
        if (!passwordEncoder.matches(user.get("userPassword"), member.getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUserPhoneNum(), member.getRoles());
    }
}