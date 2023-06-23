package com.example.AutomateX.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
public class EmailToken extends BaseTimeEntity {//각 이메일과 인증코드를 맵핑하기 위해서

    @Id @GeneratedValue
    private Long id;
    private String email;
    private String token;

    public EmailToken(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public EmailToken() {}
}
