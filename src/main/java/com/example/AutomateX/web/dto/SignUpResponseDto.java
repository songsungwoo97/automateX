package com.example.AutomateX.web.dto;

import com.example.AutomateX.config.auth.dto.SearchUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "이름, 이메일, jwt토큰을 가진 dto class", example = "{\"name\":\"name\",\"email\":\"test@email.com\",\"token\":\"1234\"}")
public class SignUpResponseDto {
  @Schema(description = "이름, 이메일 정보")
  @NonNull
  private SearchUser searchUser;
  @Schema(description = "jwt token")
  @NonNull
  private String token;
}