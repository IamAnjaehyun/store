package com.jaehyun.store.service;

import com.jaehyun.store.config.JwtTokenProvider;
import com.jaehyun.store.model.domain.Reservation;
import com.jaehyun.store.model.domain.Review;
import com.jaehyun.store.model.dto.ReviewDto;
import com.jaehyun.store.model.repository.ReservationRepository;
import com.jaehyun.store.model.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReservationRepository reservationRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ReviewRepository reviewRepository;

    public ResponseEntity<String> writeReview(ReviewDto reviewDto, HttpServletRequest request) {
        // 1. 토큰을 통한 인증 및 고객 아이디 가져오기
        String token = jwtTokenProvider.resolveToken(request);
        String userPhoneNum = jwtTokenProvider.getUserPhoneNum(token);

        // 2. 작성자 검증
        Reservation reservation = reservationRepository.findByUserPhoneNumAndStoreId(userPhoneNum, reviewDto.getStoreId());
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
        review.setUserPhoneNum(userPhoneNum);
        review.setReviewText(reviewDto.getReviewText());
        reviewRepository.save(review);

        return ResponseEntity.ok("Review created successfully.");
    }


    public ResponseEntity<String> deleteReview(Long reviewId, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String userPhoneNum = jwtTokenProvider.getUserPhoneNum(token);

        Review review = reviewRepository.findById(reviewId).orElse(null);

        // 2. 작성자 핸드폰 번호와 토큰에서 조회한 핸드폰 번호 일치 여부 확인
        if (!review.getUserPhoneNum().equals(userPhoneNum)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to delete this review.");
        }

        // 3. 리뷰 삭제
        reviewRepository.delete(review);
        return ResponseEntity.ok("Review deleted successfully.");
    }
}
