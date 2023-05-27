package com.jaehyun.store.user.domain.entity;

import com.jaehyun.store.global.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId; //리뷰 아이디

    private String userPhoneNum; //유저 아이디
    private Long storeId;//상점 아이디
    private String reviewText; //리뷰 텍스트
}
