package com.example.AutomateX.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class EPwRequest {

    private String ePw;

    public String getEPw() {
        return this.ePw;
    }
}
