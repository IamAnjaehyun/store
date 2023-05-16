package com.jaehyun.store.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId; //리뷰 아이디

    private Long userId; //유저 아이디
    private Long storeId;//상점 아이디
    private LocalDateTime createdAt; //리뷰 작성일자
    private String reviewText; //리뷰 텍스트
}
