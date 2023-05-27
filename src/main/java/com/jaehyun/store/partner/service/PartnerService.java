package com.jaehyun.store.partner.service;

import com.jaehyun.store.user.domain.entity.User;
import com.jaehyun.store.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PartnerService {
    private final UserRepository userRepository;

    //권한 변경
    public void changeRole(String userPhoneNum) {
        User member = userRepository.findByUserPhoneNum(userPhoneNum)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.setRoles(Collections.singletonList("ADMIN")); // roles를 "ADMIN"으로 변경

        userRepository.save(member);
    }
}
