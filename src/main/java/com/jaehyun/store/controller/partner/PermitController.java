package com.jaehyun.store.controller.partner;

import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Reservation;
import com.jaehyun.store.model.repository.ReservationRepository;
import com.jaehyun.store.model.type.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permit")
@RequiredArgsConstructor
public class PermitController {
    private final JwtTokenProvider jwtTokenProvider;
    private final ReservationRepository reservationRepository;

    //파트너의 전화번호를 통해 예약 목록을 조회 (이거는 권한 없어도됨 => 사실 있어야 할 것 같긴 함)
    @GetMapping("/reservations/{userPhoneNum}")
    public ResponseEntity<List<Reservation>> reservationListController(@PathVariable String userPhoneNum) {
        List<Reservation> reservations = reservationRepository.findByUserPhoneNum(userPhoneNum);
        return ResponseEntity.ok(reservations);
    }


    //예약이 들어왔을 때 승인, 혹은 거절
    //http://localhost:8080/permit/status?reservationId=1&status=OKAY 형식 (토큰값 header에 줘야함)
    @PostMapping("/status")
    public ResponseEntity<String> statusController(
            @RequestParam("reservationId") Long reservationId,
            @RequestParam("status") ReservationStatus status,
            HttpServletRequest request) {

        // 토큰에서 사장의 전화번호 추출
        String token = jwtTokenProvider.resolveToken(request);
        String phoneNum = jwtTokenProvider.getUserPhoneNum(token);

        // 예약 조회
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            // 예약한 사장의 전화번호와 토큰에서 추출한 전화번호가 일치하는지 확인
            if (reservation.getUserPhoneNum().equals(phoneNum)) {
                // 상태 변경
                reservation.setStatus(status);
                reservationRepository.save(reservation);
                return ResponseEntity.ok("Reservation status updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update the reservation status.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
