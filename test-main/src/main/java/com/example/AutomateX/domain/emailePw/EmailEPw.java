package com.example.AutomateX.domain.emailePw;

import com.example.AutomateX.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter @Entity
public class EmailEPw extends BaseTimeEntity {//각 이메일과 인증번호를 맵핑하기 위해서

    @Id @GeneratedValue
    private Long id;
    private String email;
    private String ePw;

    public EmailEPw(String email, String ePw) {
        this.email = email;
        this.ePw = ePw;
    }

    public EmailEPw() {}
}
