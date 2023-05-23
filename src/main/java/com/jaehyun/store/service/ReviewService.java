package com.jaehyun.store.service;

import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Reservation;
import com.jaehyun.store.model.domain.Review;
import com.jaehyun.store.model.dto.ReviewDto;
import com.jaehyun.store.model.repository.ReservationRepository;
import com.jaehyun.store.model.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReservationRepository reservationRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ReviewRepository reviewRepository;

    public ResponseEntity<String> writeReview(ReviewDto reviewDto, HttpServletRequest request) {
        // 1. 토큰을 통한 인증 및 고객 아이디 가져오기
        String token = jwtTokenProvider.resolveToken(request);
        String customerId = jwtTokenProvider.getUserPhoneNum(token);

        // 2. 작성자 검증
        Reservation reservation = reservationRepository.findByUserPhoneNumAndStoreId(customerId, reviewDto.getStoreId());
        if (reservation == null) {
            return ResponseEntity.badRequest().body("You are not authorized to write a review for this store.");
        }

        // 3. 예약 시간 확인
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime reservationTime = reservation.getReservationTime();
        if (currentTime.isBefore(reservationTime)) {
            return ResponseEntity.badRequest().body("You can only write a review after the reservation time has passed.");
        }

        // 4. 리뷰 작성
        Review review = new Review();
        review.setStoreId(reviewDto.getStoreId());
        review.setUserId(customerId);
        review.setReviewText(reviewDto.getReviewText());
        reviewRepository.save(review);

        return ResponseEntity.ok("Review created successfully.");
    }
}
