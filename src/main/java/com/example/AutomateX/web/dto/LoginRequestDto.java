package com.example.AutomateX.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Schema(description = "이메일, 비밀번호를 가진 dto class", example = "{\"email\":\"test@email.com\",\"password\":\"1234\"}")
public class LoginRequestDto {

    @Schema(defaultValue = "이메일")
    private String email;

    @Schema(defaultValue = "비밀번호")
    private String password;

}
