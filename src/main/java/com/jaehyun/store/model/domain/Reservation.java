package com.jaehyun.store.model.domain;

import com.jaehyun.store.model.BaseEntity;
import com.jaehyun.store.model.type.ReservationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId; //예약 id

    private Long userId; //예약요청한 회원의 id
    private Long storeId; //상점 id
    private LocalDateTime reservationTime; //예약 날짜 및 시간

    @Enumerated(EnumType.STRING)
    private ReservationStatus status= ReservationStatus.DEFAULT;; //예약 승인 여부 (기본 PLEASE_WAIT)
}
