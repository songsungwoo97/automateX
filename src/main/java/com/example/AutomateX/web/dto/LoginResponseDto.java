package com.example.AutomateX.web.dto;

import com.example.AutomateX.config.auth.dto.SearchUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LoginResponseDto {

    @Schema(description = "이름, 이메일 정보를 담음")
    private SearchUser user;

    @Schema(description = "jwt token")
    private String jwtToken;

    public LoginResponseDto(SearchUser user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }
}
