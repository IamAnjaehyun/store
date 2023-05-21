package com.jaehyun.store.model.repository;

import com.jaehyun.store.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
