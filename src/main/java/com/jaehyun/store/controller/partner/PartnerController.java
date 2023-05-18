package com.jaehyun.store.controller.partner;

import com.jaehyun.store.model.domain.User;
import com.jaehyun.store.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/partner")
@RequiredArgsConstructor
public class PartnerController {
    private final UserRepository userRepository;
    //파트너 등록
    @PostMapping("/changeRole")
    public void changeRole(@RequestParam String userPhoneNum) {
        User member = userRepository.findByUserPhoneNum(userPhoneNum)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.setRoles(Collections.singletonList("ADMIN")); // roles를 "ADMIN"으로 변경

        userRepository.save(member);
    }
}