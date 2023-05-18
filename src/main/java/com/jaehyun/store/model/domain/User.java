package com.jaehyun.store.model.domain;

import com.jaehyun.store.model.BaseEntity;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; //유저 id

    private String userName; //유저 이름
    private String userPassword;//유저 비밀번호

    @Column(unique = true)
    private String userPhoneNum;//유저 휴대폰 번호 겸 아이디

    @ColumnDefault("false")
    private boolean auth; //권한
}
