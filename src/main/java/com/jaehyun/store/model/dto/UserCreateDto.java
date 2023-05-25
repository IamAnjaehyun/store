package com.jaehyun.store.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {
    private String userPhoneNum;//유저 휴대폰 번호 겸 아이디
    private String userPassword;//유저 비밀번호
}
