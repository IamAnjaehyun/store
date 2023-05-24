package com.jaehyun.store.controller.partner;

import com.jaehyun.store.service.PartnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "파트너 등록 컨트롤러")
@RestController
@RequestMapping("/partner")
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;

    @ApiOperation(value = "파트너 권한 부여",notes = "유저의 권한을 파트너로 변경시켜 상점 등록 및 여러 권한을 갖게 합니다.")
    @PostMapping("/changeRole")
    public ResponseEntity<String> changeRole(@RequestParam String userPhoneNum) {
        partnerService.changeRole(userPhoneNum);
        return ResponseEntity.ok("changeRole successfully.");
    }
}