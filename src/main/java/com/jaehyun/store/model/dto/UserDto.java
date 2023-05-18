package com.jaehyun.store.model.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId; //유저 id

    private String userName; //유저 이름
    private String userPhoneNum;//유저 휴대폰 번호 겸 아이디
    private String userPassword;//유저 비밀번호

    private boolean auth; //권한 (있으면 파트너가입가능)
}
