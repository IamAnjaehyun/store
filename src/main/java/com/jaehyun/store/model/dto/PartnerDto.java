package com.jaehyun.store.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerDto {
    private Long partnerId; //파트너 번호

    private String partnerName; //파트너 이름
    private String partnerEmail;//파트너 email
    private String partnerPassword;//파트너 비밀번호
}