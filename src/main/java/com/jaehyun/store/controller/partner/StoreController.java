package com.jaehyun.store.controller.partner;

import com.jaehyun.store.model.domain.Store;
import com.jaehyun.store.model.dto.StoreDto;
import com.jaehyun.store.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(tags = "상점 컨트롤러")
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 상점 등록
    @ApiOperation(value = "상점 등록",notes = "파트너 권한을 가진 유저가 상점을 등록합니다.")
    @PostMapping("/register")
    public ResponseEntity<String> registerStore(@RequestBody StoreDto storeDto, HttpServletRequest request) {
        storeService.registerStore(storeDto, request);
        return ResponseEntity.ok("registerStore successfully.");
    }

    //상점 삭제
    @ApiOperation(value = "상점 삭제",notes = "파트너 권한을 가진 유저가 상점을 삭제합니다.")
    @DeleteMapping("/delete/{storeId}")
    public ResponseEntity<String> deleteStore(@PathVariable Long storeId, HttpServletRequest request) {
        storeService.deleteStore(storeId, request);
        return ResponseEntity.ok("deleteStore successfully.");
    }

    //상점 개별 조회
    @ApiOperation(value = "상점 조회",notes = "상점의 이름을 통해 상점을 조회합니다.")
    @GetMapping("/view/{storeName}")
    public ResponseEntity<List<Store>> getStore(@PathVariable String storeName) {
        List<Store> store = storeService.viewStore(storeName);
        return ResponseEntity.ok(store);
    }

    // 모든 매장 정보 확인
    @ApiOperation(value = "상점 전체 조회",notes = "모든 매장의 목록을 확인합니다.")
    @GetMapping("/view")
    public List<Map<String, Object>> getAllStores() {
        return storeService.getAllStores();
    }
}
