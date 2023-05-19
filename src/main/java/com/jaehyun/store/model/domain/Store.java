package com.jaehyun.store.model.domain;

import com.jaehyun.store.model.BaseEntity;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;//매장 번호

    private String storeName; //매장 이름
    private String storeLocation; //매장 위치
    private String storeDescription; //매장 설명
}
