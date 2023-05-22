package com.jaehyun.store.service;

import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Reservation;
import com.jaehyun.store.model.repository.ReservationRepository;
import com.jaehyun.store.model.type.EarlyCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<String> createReservation(Long storeId, LocalDateTime reservationTime, HttpServletRequest request) {
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

    public ResponseEntity<String> checkReservation(String userPhoneNum) {
        if (userPhoneNum != null && !userPhoneNum.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();

            List<Reservation> reservations = reservationRepository.findByUserPhoneNumAndReservationTimeBefore(userPhoneNum, now);

            for (Reservation reservation : reservations) {
                reservation.setComeCheck(EarlyCheck.COME);
                reservationRepository.save(reservation);
            }

            return ResponseEntity.ok("Reservations checked successfully.");
        } else {
            return ResponseEntity.badRequest().body("Missing 'userPhoneNum' parameter.");
        }
    }
}
