// PermitService.java
package com.jaehyun.store.partner.service;

import com.jaehyun.store.global.config.JwtTokenProvider;
import com.jaehyun.store.user.domain.entity.Reservation;
import com.jaehyun.store.partner.domain.entity.Store;
import com.jaehyun.store.user.domain.repository.ReservationRepository;
import com.jaehyun.store.partner.domain.repository.StoreRepository;
import com.jaehyun.store.global.type.ReservationStatus;
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
    private final StoreRepository storeRepository;

    // 파트너의 전화번호를 통해 예약 목록을 조회
    public List<Reservation> getReservationsByUserPhoneNum(String userPhoneNum) {
        return reservationRepository.findByUserPhoneNum(userPhoneNum);
    }

    // 토큰에서 전화번호 추출
    public String extractUserPhoneNumFromToken(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        return jwtTokenProvider.getUserPhoneNum(token);
    }

    // 예약 상태 업데이트
    public boolean updateReservationStatus(Long reservationId, HttpServletRequest request, ReservationStatus status) {
        // 토큰을 통해 상점 주인의 userPhoneNum 가져오기
        String token = jwtTokenProvider.resolveToken(request);
        String storeOwnerUserPhoneNum = jwtTokenProvider.getUserPhoneNum(token);

        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            Optional<Store> optionalStore = storeRepository.findByStoreId(reservation.getStoreId());
            if (optionalStore.isPresent() && optionalStore.get().getUserPhoneNum().equals(storeOwnerUserPhoneNum)) {
                // 예약 상태 업데이트 로직
                reservation.setStatus(status);
                reservationRepository.save(reservation);
                return true;
            }
        }
        return false;
    }
}
