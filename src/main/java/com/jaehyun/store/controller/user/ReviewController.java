package com.jaehyun.store.controller.user;

import com.jaehyun.store.model.dto.ReviewDto;
import com.jaehyun.store.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "리뷰 컨트롤러")
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 작성 컨트롤러
    @ApiOperation(value = "리뷰 작성",notes = "손님이 자신이 이용한 상점에 대해 리뷰를 남길 수 있게 합니다.")
    @PostMapping("/reviews")
    public ResponseEntity<String> writeReview(@RequestBody ReviewDto reviewDto, HttpServletRequest request) {
        return reviewService.writeReview(reviewDto, request);
    }
}
