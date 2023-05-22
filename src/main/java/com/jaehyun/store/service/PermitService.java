// PermitService.java
package com.jaehyun.store.service;

import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Reservation;
import com.jaehyun.store.model.repository.ReservationRepository;
import com.jaehyun.store.model.type.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermitService {

    private final JwtTokenProvider jwtTokenProvider;
    private final ReservationRepository reservationRepository;

    // 파트너의 전화번호를 통해 예약 목록을 조회
    public List<Reservation> getReservationsByUserPhoneNum(String userPhoneNum) {
        return reservationRepository.findByUserPhoneNum(userPhoneNum);
    }

    // 토큰에서 파트너의 전화번호 추출
    public String extractUserPhoneNumFromToken(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        return jwtTokenProvider.getUserPhoneNum(token);
    }

    // 예약 상태 업데이트
    public boolean updateReservationStatus(Long reservationId, String phoneNum, ReservationStatus status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            if (reservation.getUserPhoneNum().equals(phoneNum)) {
                reservation.setStatus(status);
                reservationRepository.save(reservation);
                return true;
            }
        }
        return false;
    }
}
