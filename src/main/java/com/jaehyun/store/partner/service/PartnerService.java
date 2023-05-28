package com.jaehyun.store.partner.service;

import com.jaehyun.store.global.config.JwtTokenProvider;
import com.jaehyun.store.partner.domain.entity.Store;
import com.jaehyun.store.partner.domain.repository.StoreRepository;
import com.jaehyun.store.type.ReservationStatus;
import com.jaehyun.store.user.domain.entity.Reservation;
import com.jaehyun.store.user.domain.entity.User;
import com.jaehyun.store.user.domain.repository.ReservationRepository;
import com.jaehyun.store.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;

    //권한 변경
    public void changeRole(String userPhoneNum) {
        User member = userRepository.findByUserPhoneNum(userPhoneNum)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.setRoles(Collections.singletonList("ADMIN")); // roles를 "ADMIN"으로 변경

        userRepository.save(member);
    }

    // 파트너의 전화번호를 통해 예약 목록을 조회
    public List<Reservation> getReservationsByPartnerPhoneNum(String partnerPhoneNum) {
        // 상점 주인의 userPhoneNum을 통해 상점들을 조회
        List<Store> stores = storeRepository.findByUserPhoneNum(partnerPhoneNum);

        // 상점들의 storeId 목록을 추출
        List<Long> storeIds = stores.stream()
                .map(Store::getStoreId)
                .collect(Collectors.toList());

        // 상점들의 storeId 목록을 통해 예약 목록을 조회
        return reservationRepository.findByStoreIdIn(storeIds);
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
