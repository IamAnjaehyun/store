package com.jaehyun.store.user.service;

import com.jaehyun.store.global.config.JwtTokenProvider;
import com.jaehyun.store.global.exception.impl.reservation.NotExistReservationException;
import com.jaehyun.store.global.exception.impl.store.NotExistStoreException;
import com.jaehyun.store.global.exception.impl.user.NotExistUserException;
import com.jaehyun.store.partner.domain.entity.Store;
import com.jaehyun.store.partner.domain.repository.StoreRepository;
import com.jaehyun.store.type.EarlyCheck;
import com.jaehyun.store.user.domain.entity.Reservation;
import com.jaehyun.store.user.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final StoreRepository storeRepository;

    //예약 생성
    @Transactional
    public void createReservation(String storeName, LocalDateTime reservationTime, HttpServletRequest request) {
        // 토큰으로 phoneNum 가져오기
        String token = jwtTokenProvider.resolveToken(request);
        String phoneNum = jwtTokenProvider.getUserPhoneNum(token);

        // 상점 이름으로 storeId 조회
        Store store = storeRepository.findByStoreName(storeName);
        if (store == null) {
            // 상점이 존재하지 않을 경우 처리
            throw new NotExistStoreException();
        }
        // 예약 생성을 위해 필요한 값들을 사용하여 Reservation 엔티티를 생성
        Reservation reservation = Reservation.builder()
            .userPhoneNum(phoneNum)
            .storeName(storeName)
            .storeId(store.getStoreId())
            .reservationTime(reservationTime)
            .build();

        // 예약 데이터를 데이터베이스에 저장
        reservationRepository.save(reservation);
    }


    //예약 취소
    @Transactional
    public void cancelReservation(String storeName, HttpServletRequest request) {
        if (!storeRepository.existsByStoreName(storeName)) {
            throw new NotExistStoreException();
        }
        // 토큰으로 phoneNum 가져오기
        String token = jwtTokenProvider.resolveToken(request);
        String phoneNum = jwtTokenProvider.getUserPhoneNum(token);

        int cancelCnt = reservationRepository.deleteByUserPhoneNumAndStoreName(phoneNum, storeName);
        if (cancelCnt == 0) {
            throw new NotExistReservationException();
        }

    }

    //10분전에 와서 확인
    @Transactional
    public void checkReservation(String userPhoneNum) {
        if (userPhoneNum != null && !userPhoneNum.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            List<Reservation> reservations = reservationRepository.findByUserPhoneNumAndReservationTimeBefore(userPhoneNum, now);

            for (Reservation reservation : reservations) {
                reservation.setComeCheck(EarlyCheck.COME);
                reservationRepository.save(reservation);
            }
        } else {
            throw new NotExistUserException();
        }
    }
}
