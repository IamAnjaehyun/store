package com.jaehyun.store.controller.partner;

import com.jaehyun.store.model.domain.Reservation;
import com.jaehyun.store.model.type.ReservationStatus;
import com.jaehyun.store.service.PermitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "권한 승인 컨트롤러")
@RestController
@RequestMapping("/permit")
@RequiredArgsConstructor
public class PermitController {

    private final PermitService permitService;

    // 파트너의 전화번호를 통해 예약 목록을 조회
    @ApiOperation(value = "예약 목록 조회",notes = "점주의 휴대폰 번호와 일치하는 정보를 가진 가게에 대한 예약 목록을 조회합니다.")
    @GetMapping("/reservations/{userPhoneNum}")
    public ResponseEntity<List<Reservation>> viewReservationList(@PathVariable String userPhoneNum) {
        List<Reservation> reservations = permitService.getReservationsByUserPhoneNum(userPhoneNum);
        return ResponseEntity.ok(reservations);
    }

    // 들어온 예약에 대해 승인 또는 거절
    @ApiOperation(value = "예약 승인 혹은 거절",notes = "예약이 들어왔을 때 점주가 예약 대기 상태를 예약 승인 혹은 거절 상태로 지정합니다.")
    @PostMapping("/status")
    public ResponseEntity<String> setStatus(
            @RequestParam("reservationId") Long reservationId,
            @RequestParam("status") ReservationStatus status,
            HttpServletRequest request) {

        // 토큰에서 전화번호 추출
//        String phoneNum = permitService.extractUserPhoneNumFromToken(request);

        // 예약 상태 업데이트
        boolean updated = permitService.updateReservationStatus(reservationId, request, status);
        if (updated) {
            return ResponseEntity.ok("Reservation status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update the reservation status.");
        }
    }
}
