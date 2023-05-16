package com.jaehyun.store.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private Long reviewId; //리뷰 아이디

    private Long userId; //유저 아이디
    private Long storeId;//상점 아이디
    private LocalDateTime createdAt; //리뷰 작성일자
    private String reviewText; //리뷰 텍스트
}
