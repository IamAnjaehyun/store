package com.jaehyun.store.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {
    private Long storeId;//매장 번호

    private String storeName; //매장 이름
    private String storeLocation; //매장 위치
    private String storeDescription; //매장 설명

    public StoreDto(String storeName, String storeLocation, String storeDescription) {
        this.storeName = storeName;
        this.storeLocation = storeLocation;
        this.storeDescription = storeDescription;
    }

}
