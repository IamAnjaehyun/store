package com.jaehyun.store.controller.partner;

import com.jaehyun.store.model.domain.Reservation;
import com.jaehyun.store.model.type.ReservationStatus;
import com.jaehyun.store.service.PermitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/permit")
@RequiredArgsConstructor
public class PermitController {

    private final PermitService permitService;

    // 파트너의 전화번호를 통해 예약 목록을 조회
    @GetMapping("/reservations/{userPhoneNum}")
    public ResponseEntity<List<Reservation>> viewReservationList(@PathVariable String userPhoneNum) {
        List<Reservation> reservations = permitService.getReservationsByUserPhoneNum(userPhoneNum);
        return ResponseEntity.ok(reservations);
    }

    // 들어온 예약에 대해 승인 또는 거절
    @PostMapping("/status")
    public ResponseEntity<String> setStatus(
            @RequestParam("reservationId") Long reservationId,
            @RequestParam("status") ReservationStatus status,
            HttpServletRequest request) {

        // 토큰에서 파트너의 전화번호 추출
        String phoneNum = permitService.extractUserPhoneNumFromToken(request);

        // 예약 상태 업데이트
        boolean updated = permitService.updateReservationStatus(reservationId, phoneNum, status);
        if (updated) {
            return ResponseEntity.ok("Reservation status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update the reservation status.");
        }
    }
}
