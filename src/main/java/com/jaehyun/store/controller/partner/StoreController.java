package com.jaehyun.store.controller.partner;

import com.jaehyun.store.model.dto.StoreDto;
import com.jaehyun.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 상점 등록
    @PostMapping("/register")
    public ResponseEntity<String> registerStore(@RequestBody StoreDto storeDto, HttpServletRequest request) {
        return storeService.registerStore(storeDto, request);
    }

    // 모든 매장 정보 확인
    @GetMapping("/view")
    public List<Map<String, Object>> getAllStores() {
        return storeService.getAllStores();
    }
}
