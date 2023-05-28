package com.jaehyun.store.partner.domain.entity;

import com.jaehyun.store.global.BaseEntity;
import com.jaehyun.store.type.StarRating;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;//매장 번호
    private String userPhoneNum; //사장인지 확인하기 위한 사장 핸드폰번호(여기선 ID로 쓰임)

    @Column(unique = true)
    private String storeName; //매장 이름

    private String storeLocation; //매장 위치
    private String storeDescription; //매장 설명

    private int distance; //매장으로부터의 거리 (javaScript를 통해 내 위치를 가져올 수 없어, 거리를 직접 지정하여 거리순으로 sort 할 예정)
    //    @Enumerated(EnumType.STRING)
//    private StarRating rating; //별점은 Enum으로 정의하여 1~5점만 가능하도록
    private StarRating averageRating;
    private int reviewCount;
}
