package com.jaehyun.store.model.repository;

import com.jaehyun.store.model.domain.Reservation;
import com.jaehyun.store.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserPhoneNum(String userPhoneNum);

}
