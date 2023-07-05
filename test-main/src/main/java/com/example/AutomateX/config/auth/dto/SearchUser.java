package com.example.AutomateX.config.auth.dto;

import com.example.AutomateX.domain.user.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Schema(description = "이름, 이메일을 가진 dto class", example = "{\"name\":\"name\",\"email\":\"test@email.com\"}")
public class SearchUser implements Serializable {
  @Schema(description = "이름")
  private String name;
  @Schema(description = "이메일")
  private String email;

  @Builder
  public SearchUser(Account user) {
    this.name = user.getName();
    this.email = user.getEmail();
  }
}