package com.jaehyun.store.service;

import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Store;
import com.jaehyun.store.model.dto.StoreDto;
import com.jaehyun.store.model.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    //상점 등록
    public ResponseEntity<String> registerStore(StoreDto storeDto, HttpServletRequest request) {
        // 토큰을 통해 사용자 정보를 가져옴
        String token = jwtTokenProvider.resolveToken(request);

        // 권한이 ADMIN인지 확인(=PARTNER 등록이 되어있는지)
        if (jwtTokenProvider.userHasAdminRole(token)) { // 권한이 있어야 store 등록 가능
            Store store = new Store();
            store.setStoreName(storeDto.getStoreName());
            store.setStoreLocation(storeDto.getStoreLocation());
            store.setStoreDescription(storeDto.getStoreDescription());
            // 사장의 전화번호를 토큰에서 추출하여 설정
            String userPhoneNum = jwtTokenProvider.getUserPhoneNum(token);
            store.setUserPhoneNum(userPhoneNum);

            storeRepository.save(store);
            return ResponseEntity.ok("Store registered successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only ADMIN users can register a store.");
        }
    }

    //상점 삭제
    public boolean deleteStore(Long storeId, HttpServletRequest request) {
        // 토큰을 통해 사용자 정보를 가져옴
        String token = jwtTokenProvider.resolveToken(request);
        String userPhoneNum = jwtTokenProvider.getUserPhoneNum(token);
        Store store = storeRepository.findById(storeId).orElse(null);
        String storeUserPhoneNum = store.getUserPhoneNum();
        // 권한이 ADMIN인지 확인(=PARTNER 등록이 되어있는지) && userPhoneNum 과 storeUserPhoneNum이 같은지
        if (jwtTokenProvider.userHasAdminRole(token) && userPhoneNum.equals(storeUserPhoneNum)) { // 권한이 있어야 store 삭제 가능
            storeRepository.deleteById(storeId);
            return true;
        }else {
            return false;
        }
    }

    //상점 수정
    public boolean updateStore(Long storeId, StoreDto storeDto, HttpServletRequest request) {
        // 토큰을 통해 사용자 정보를 가져옴
        String token = jwtTokenProvider.resolveToken(request);
        String userPhoneNum = jwtTokenProvider.getUserPhoneNum(token);
        Store store = storeRepository.findById(storeId).orElse(null);
        String storeUserPhoneNum = store.getUserPhoneNum();
        // 권한이 ADMIN인지 확인(=PARTNER 등록이 되어있는지) && userPhoneNum 과 storeUserPhoneNum이 같은지
        if (jwtTokenProvider.userHasAdminRole(token) && userPhoneNum.equals(storeUserPhoneNum)) { // 권한이 있어야 store 수정 가능
            store.setStoreName(storeDto.getStoreName());
            store.setStoreDescription(storeDto.getStoreDescription());
            store.setStoreLocation(storeDto.getStoreLocation());
            storeRepository.save(store);
            return true;
        }else {
            return false;
        }
    }

    //상점 이름으로 조회
    public List<Store> viewStore(String storeName) {
        return storeRepository.findByStoreName(storeName);
    }


    //상점 조회
    public List<Map<String, Object>> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Store store : stores) { // 원하는 정보만 가져올 수 있도록 (필요없는 등록일자, 수정일자등은 삭제)
            Map<String, Object> storeInfo = new HashMap<>();
            storeInfo.put("storeId", store.getStoreId());
            storeInfo.put("storeName", store.getStoreName());
            storeInfo.put("storeLocation", store.getStoreLocation());
            result.add(storeInfo);
        }

        return result;
    }
}
