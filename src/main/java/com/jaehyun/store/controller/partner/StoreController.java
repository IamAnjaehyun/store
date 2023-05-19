package com.jaehyun.store.controller.partner;


import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Store;
import com.jaehyun.store.model.dto.StoreDto;
import com.jaehyun.store.model.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/partner")
@RequiredArgsConstructor
public class StoreController {
    private final StoreRepository storeRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @PostMapping("/register")
    public ResponseEntity<String> registerStore(@RequestBody StoreDto storeDto, HttpServletRequest request) {
        // 토큰을 해석하여 사용자 정보를 가져옵니다.
        String token = jwtTokenProvider.resolveToken(request);

        // 권한이 ADMIN인지 확인합니다.
        if (jwtTokenProvider.userHasAdminRole(token)) {
            // Store 등록 로직을 작성합니다.
            // ...
            Store store = new Store();
            store.setStoreName(storeDto.getStoreName());
            store.setStoreLocation(storeDto.getStoreLocation());
            store.setStoreDescription(storeDto.getStoreDescription());
            storeRepository.save(store);
            return ResponseEntity.ok("Store registered successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only ADMIN users can register a store.");
        }
    }
}

