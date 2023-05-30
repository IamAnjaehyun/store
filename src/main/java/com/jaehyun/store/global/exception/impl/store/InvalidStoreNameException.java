package com.jaehyun.store.global.exception.impl.store;

import com.jaehyun.store.global.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class InvalidStoreNameException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "상점 이름이 정확하지 않습니다.";
    }
}
