package com.jaehyun.store.controller.partner;


import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Store;
import com.jaehyun.store.model.dto.StoreDto;
import com.jaehyun.store.model.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class StoreController {
    private final StoreRepository storeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public void addStore(HttpServletRequest request, @RequestBody StoreDto storeDto) {
        String token = jwtTokenProvider.resolveToken(request);
        if (jwtTokenProvider.validateToken(token)) {
            Store store = new Store();
            store.setStoreName(storeDto.getStoreName());
            store.setStoreLocation(storeDto.getStoreLocation());
            store.setStoreDescription(storeDto.getStoreDescription());

            storeRepository.save(store);
        } else {
            // 토큰이 유효하지 않은 경우에 대한 처리
            throw new IllegalArgumentException("Invalid token");
        }
    }
}