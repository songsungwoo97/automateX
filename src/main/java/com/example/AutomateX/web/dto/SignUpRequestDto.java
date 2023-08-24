package com.example.AutomateX.web.dto;

import com.example.AutomateX.domain.user.Account;
import com.example.AutomateX.domain.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Schema(description = "이름, 이메일, 비밀번호를 가진 dto class", example = "{\"name\":\"name\",\"email\":\"test@email.com\",\"password\":\"1234\"}")
public class SignUpRequestDto {
  @NotBlank(message = "사용자 이름은 필수항목입니다.")
  @Schema(defaultValue = "이름")
  private String name;
  @NotBlank(message = "이메일은 필수항목입니다.")
  @Email
  @Schema(defaultValue = "이메일")
  private String email;
  @NotBlank(message = "비밀번호는 필수항목입니다.")
  @Schema(defaultValue = "비밀번호")
  private String password;

  private Role role;

  @Builder
  public SignUpRequestDto(String name, String email, String password, Role role) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public Account toEntity() {
    return Account.builder()
            .name(name)
            .email(email)
            .password(password)
            .role(role)
            .build();
  }
}