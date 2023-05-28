package com.jaehyun.store.user.service;

import com.jaehyun.store.global.config.JwtTokenProvider;
import com.jaehyun.store.partner.domain.entity.Store;
import com.jaehyun.store.partner.domain.repository.StoreRepository;
import com.jaehyun.store.user.domain.dto.ReviewDto;
import com.jaehyun.store.user.domain.entity.Review;
import com.jaehyun.store.user.domain.repository.ReservationRepository;
import com.jaehyun.store.user.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReservationRepository reservationRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public ResponseEntity<String> writeReview(ReviewDto reviewDto, HttpServletRequest request) {
        // 토큰으로부터 사용자 핸드폰 번호 가져오기
        String token = jwtTokenProvider.resolveToken(request);
        String userPhoneNum = jwtTokenProvider.getUserPhoneNum(token);

        // 상점 ID 조회
        String storeName = reviewDto.getStoreName();
        Store store = storeRepository.findByStoreName(storeName);
        if (store == null) {
            return ResponseEntity.badRequest().body("can't find store.");
        }

        // 새로운 리뷰 엔티티 생성
        Review review = new Review();
        review.setUserPhoneNum(userPhoneNum);
        review.setStoreId(store.getStoreId());
        review.setStoreName(store.getStoreName());
        review.setReviewText(reviewDto.getReviewText());
        review.setStarRating(reviewDto.getStarRating());

        // 리뷰를 데이터베이스에 저장
        reviewRepository.save(review);

        // 상점의 총 별점 총점과 리뷰 개수 업데이트
        double newTotalRating = store.getTotalRating() + review.getStarRating();
        int newTotalReviewCount = store.getTotalReviewCount() + 1;

        // 평균 점수 계산
        double newAverageRating = newTotalRating / newTotalReviewCount;

        if (newAverageRating > 5.0) {
            newAverageRating = 5.0; // 평균 점수가 5를 초과하지 않도록 제한
        } else if (newAverageRating < 0.0) {
            newAverageRating = 0.0; // 평균 점수가 0보다 작아지지 않도록 제한
        }

        // 상점 엔티티에 업데이트된 평균 점수와 리뷰 개수 설정
        store.setTotalRating((long) newTotalRating);
        store.setTotalReviewCount(newTotalReviewCount);
        store.setAverageRating(newAverageRating);

        storeRepository.save(store);

        return ResponseEntity.ok("리뷰가 성공적으로 작성되었습니다.");
    }
    //리뷰 삭제
    public ResponseEntity<String> deleteReview(Long reviewId, HttpServletRequest request) {
        //토큰에서 리뷰 작성자의 id 가져옴
        String token = jwtTokenProvider.resolveToken(request);
        String userPhoneNum = jwtTokenProvider.getUserPhoneNum(token);

        //id로 리뷰를 찾음
        Review review = reviewRepository.findById(reviewId).orElse(null);

        //작성자 핸드폰 번호와 토큰에서 조회한 핸드폰 번호 일치 여부 확인
        if (!Objects.requireNonNull(review).getUserPhoneNum().equals(userPhoneNum)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to delete this review.");
        }

        //리뷰 삭제
        reviewRepository.delete(review);
        return ResponseEntity.ok("Review deleted successfully.");
    }

    //상점 이름으로 상점에 대한 모든 리뷰 조회
    public List<Review> viewReview(String storeName) {
        //상점 조회
        Store store = storeRepository.findByStoreName(storeName);
        if (store == null) {
            // 상점이 존재하지 않을 경우 빈 리뷰 목록 반환
            return Collections.emptyList();
        }
        //리뷰 조회
        return reviewRepository.findByStoreId(store.getStoreId());
    }

}
