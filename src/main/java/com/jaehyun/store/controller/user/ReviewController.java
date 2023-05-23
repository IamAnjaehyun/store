// ReviewController.java
package com.jaehyun.store.controller.user;

import com.jaehyun.store.model.dto.ReviewDto;
import com.jaehyun.store.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 작성 컨트롤러
    @PostMapping("/reviews")
    public ResponseEntity<String> writeReview(@RequestBody ReviewDto reviewDto, HttpServletRequest request) {
        return reviewService.writeReview(reviewDto, request);
    }

//    @PostMapping("/delete")
//    public ResponseEntity<String> deleteReview(@RequestBody ReviewDto reviewDto, HttpServletRequest request) {
//        return reviewService.writeReview(reviewDto, request);
//    }
}
