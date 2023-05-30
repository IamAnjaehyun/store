package com.jaehyun.store.global.exception.impl.reservation;

import com.jaehyun.store.global.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NotExistReservationException extends AbstractException{
        @Override
        public int getStatusCode() {
            return HttpStatus.BAD_REQUEST.value();
        }

        @Override
        public String getMessage() {
            return "존재하지 않는 예약입니다.";
        }
}
