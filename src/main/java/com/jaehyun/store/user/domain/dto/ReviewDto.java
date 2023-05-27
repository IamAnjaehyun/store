package com.jaehyun.store.user.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    //유저 아이디는 토큰을 통해서 가져오면 됨. 생성시간은 알아서 생김. BaseEntity로인해
    private String storeName; //상점 이름
    private String reviewText; //리뷰 텍스트
}
