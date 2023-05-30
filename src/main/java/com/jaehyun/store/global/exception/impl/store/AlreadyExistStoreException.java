package com.jaehyun.store.global.exception.impl.store;

import com.jaehyun.store.global.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class AlreadyExistStoreException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "이미 존재하는 상점 이름입니다.";
    }
}