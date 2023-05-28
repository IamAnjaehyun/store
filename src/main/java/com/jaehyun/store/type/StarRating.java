package com.jaehyun.store.type;

public enum StarRating {
    ONE_STAR(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);

    private final int value;

    StarRating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StarRating fromValue(int value) {
        for (StarRating rating : values()) {
            if (rating.getValue() == value) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Invalid StarRating value: " + value);
    }
}
