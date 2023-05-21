package com.jaehyun.store.model.domain;

import com.jaehyun.store.model.BaseEntity;
import com.jaehyun.store.model.type.EarlyCheck;
import com.jaehyun.store.model.type.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId; //예약 id

    private String userPhoneNum; //예약요청한 회원의 id(=phoneNum)
    private Long storeId; //상점 id
    private LocalDateTime reservationTime; //예약 날짜 및 시간

    @Enumerated(EnumType.STRING)
    private ReservationStatus status= ReservationStatus.DEFAULT; //예약 승인 여부 (기본 PLEASE_WAIT)

    @Enumerated(EnumType.STRING)
    private EarlyCheck comeCheck= EarlyCheck.DEFAULT; //10분전 체크 (기본 NOT_CHECK)
}
