package com.jaehyun.store.model.domain;

import com.jaehyun.store.model.BaseEntity;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId; //리뷰 아이디

    private String userId; //유저 아이디
    private Long storeId;//상점 아이디
    private String reviewText; //리뷰 텍스트
}
