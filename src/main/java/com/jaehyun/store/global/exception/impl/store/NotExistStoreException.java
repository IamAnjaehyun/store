package com.jaehyun.store.global.exception.impl.store;

import com.jaehyun.store.global.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NotExistStoreException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "존재하지 않는 상점입니다.";
    }
}