package com.jaehyun.store.model.repository;

import com.jaehyun.store.model.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserPhoneNum(String userPhoneNum);

    List<Reservation> findByUserPhoneNumAndReservationTimeBefore(String userPhoneNum, LocalDateTime reservationTime);

    Reservation findByUserPhoneNumAndStoreId(String userPhoneNum, Long storeId);
}
