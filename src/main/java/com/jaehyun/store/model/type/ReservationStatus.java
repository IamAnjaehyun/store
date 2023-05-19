package com.jaehyun.store.model.type;

public enum ReservationStatus {
    REFUSE,
    PLEASE_WAIT,
    OKAY;

    public static final ReservationStatus DEFAULT = PLEASE_WAIT;
}
