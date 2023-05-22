package com.jaehyun.store.controller.user;

import com.jaehyun.store.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // 예약 생성
    @PostMapping("/createReservation")
    public ResponseEntity<String> createReservation(
            @RequestParam("storeId") Long storeId,
            @RequestParam("reservationTime") @DateTimeFormat(pattern = "yy-MM-dd'T'HH:mm") LocalDateTime reservationTime,
            HttpServletRequest request) {
        return reservationService.createReservation(storeId, reservationTime, request);
    }

    // 예약 체크
    @GetMapping("/check")
    public ResponseEntity<String> checkReservation(@RequestParam("userPhoneNum") String userPhoneNum) {
        return reservationService.checkReservation(userPhoneNum);
    }
}
