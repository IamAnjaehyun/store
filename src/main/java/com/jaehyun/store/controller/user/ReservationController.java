package com.jaehyun.store.controller.user;

import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Reservation;
import com.jaehyun.store.model.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationRepository reservationRepository;

    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/createReservation")
    public ResponseEntity<String> createReservation(
            @RequestParam("storeId") Long storeId,
            @RequestParam("reservationTime") @DateTimeFormat(pattern = "yy-MM-dd'T'HH:mm") LocalDateTime reservationTime,
            HttpServletRequest request) {

        // 토큰에서 사용자 정보를 추출하여 회원의 전화번호 가져오기
        String token = jwtTokenProvider.resolveToken(request);
        String phoneNum = jwtTokenProvider.getUserPhoneNum(token);

        // 예약 생성을 위해 필요한 값들을 사용하여 Reservation 엔티티를 생성
        Reservation reservation = new Reservation();
        reservation.setUserPhoneNum(phoneNum);
        reservation.setStoreId(storeId);
        reservation.setReservationTime(reservationTime);

        // 예약 데이터를 데이터베이스에 저장
        reservationRepository.save(reservation);

        return ResponseEntity.ok("Reservation created successfully");
    }

}
