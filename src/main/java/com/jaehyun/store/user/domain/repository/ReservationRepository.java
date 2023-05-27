package com.jaehyun.store.user.domain.repository;

import com.jaehyun.store.user.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserPhoneNum(String userPhoneNum);
    List<Reservation> findByStoreIdIn(List<Long> storeIds);

    //폰번호로 예약시간 조회
    List<Reservation> findByUserPhoneNumAndReservationTimeBefore(String userPhoneNum, LocalDateTime reservationTime);

    Reservation deleteByUserPhoneNumAndStoreName(String userPhoneNum, String storeName);

    Reservation findByUserPhoneNumAndStoreId(String userPhoneNum, Long storeId);
}
