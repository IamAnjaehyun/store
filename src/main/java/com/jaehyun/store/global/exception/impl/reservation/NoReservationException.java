package com.jaehyun.store.global.exception.impl.reservation;

import com.jaehyun.store.global.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NoReservationException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "예약이 전혀 존재하지 않습니다.";
    }
}
