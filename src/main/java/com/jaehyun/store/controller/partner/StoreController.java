package com.jaehyun.store.controller.partner;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Store;
import com.jaehyun.store.model.dto.StoreDto;
import com.jaehyun.store.model.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreRepository storeRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

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


    @GetMapping("/view")
    public List<Map<String, Object>> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Store store : stores) {
            Map<String, Object> storeInfo = new HashMap<>();
            storeInfo.put("storeId", store.getStoreId());
            storeInfo.put("storeName", store.getStoreName());
            storeInfo.put("storeLocation", store.getStoreLocation());
            result.add(storeInfo);
        }

        return result;
    }
}

