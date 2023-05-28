package com.jaehyun.store.user.domain.repository;

import com.jaehyun.store.user.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserPhoneNum(String userPhoneNum);
    List<Review> findByStoreId(Long storeId);
}
