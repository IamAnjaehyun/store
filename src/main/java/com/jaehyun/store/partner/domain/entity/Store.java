package com.jaehyun.store.partner.domain.entity;

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
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;//매장 번호
    private String userPhoneNum; //사장인지 확인하기 위한 사장 핸드폰번호(여기선 ID로 쓰임)

    private String storeName; //매장 이름
    private String storeLocation; //매장 위치
    private String storeDescription; //매장 설명
}
