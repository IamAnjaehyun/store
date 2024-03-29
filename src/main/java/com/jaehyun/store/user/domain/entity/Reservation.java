package com.jaehyun.store.user.domain.entity;

import com.jaehyun.store.global.BaseEntity;
import com.jaehyun.store.type.EarlyCheck;
import com.jaehyun.store.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Entity
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId; //예약 id

    private String userPhoneNum; //예약요청한 회원의 id(=phoneNum)
    private Long storeId; //상점 id
    private String storeName; //상점 이름
    private LocalDateTime reservationTime; //예약 날짜 및 시간

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.DEFAULT; //예약 승인 여부 (기본 PLEASE_WAIT)

    @Enumerated(EnumType.STRING)
    private EarlyCheck comeCheck = EarlyCheck.DEFAULT; //10분전 체크 (기본 NOT_CHECK)
}
