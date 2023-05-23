package com.jaehyun.store.controller.partner;

import com.jaehyun.store.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partner")
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;

    @PostMapping("/changeRole")
    public ResponseEntity<String> changeRole(@RequestParam String userPhoneNum) {
        partnerService.changeRole(userPhoneNum);
        return ResponseEntity.ok("changeRole successfully.");
    }
}
