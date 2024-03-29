package com.jaehyun.store.user.domain.entity;

import com.jaehyun.store.global.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Entity
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId; //리뷰 아이디

    private String userPhoneNum; //유저 아이디
    private Long storeId;//상점 아이디
    private String storeName;//상점 이름
    private String reviewText; //리뷰 텍스트
    private int starRating; //별점
}
