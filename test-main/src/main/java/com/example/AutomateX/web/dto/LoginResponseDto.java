package com.example.AutomateX.web.dto;

import com.example.AutomateX.config.auth.dto.SearchUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {
    private SearchUser user;
    private String jwtToken;
}
