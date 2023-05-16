package com.jaehyun.store.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId; //예약 id

    private Long userId; //예약요청한 회원의 id
    private Long storeId; //상점 id
    private LocalDateTime reservationTime; //예약 날짜 및 시간
    private boolean isConfirmed; //예약 승인 여부
}
