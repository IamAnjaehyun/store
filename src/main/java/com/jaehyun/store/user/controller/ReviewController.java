package com.jaehyun.store.user.controller;

import com.jaehyun.store.user.domain.dto.ReviewDto;
import com.jaehyun.store.user.domain.entity.Review;
import com.jaehyun.store.user.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "리뷰 컨트롤러")
@RestController
@RequestMapping("/shop/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @ApiOperation(value = "리뷰 작성", notes = "손님이 자신이 이용한 상점에 대해 리뷰를 남길 수 있게 합니다.")
    @PostMapping("/reviews")
    public ResponseEntity<String> writeReview(@RequestBody ReviewDto reviewDto, HttpServletRequest request) {
        return reviewService.writeReview(reviewDto, request);
    }

    @ApiOperation(value = "리뷰 삭제", notes = "손님이 자신이 이용한 상점에 대해 리뷰를 삭제할 수 있게 합니다.")
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, HttpServletRequest request) {
        return reviewService.deleteReview(reviewId, request);
    }

    @ApiOperation(value = "리뷰 조회", notes = "상점 이름을 입력하여, 해당 상점의 리뷰를 조회한다.")
    @GetMapping("view/{storeName}")
    public List<Review> viewReview(@PathVariable String storeName) {
        return reviewService.viewReview(storeName);
    }
}
