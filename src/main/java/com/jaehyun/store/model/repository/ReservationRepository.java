package com.jaehyun.store.model.repository;

import com.jaehyun.store.model.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
