package com.jaehyun.store.model.repository;

import com.jaehyun.store.model.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
