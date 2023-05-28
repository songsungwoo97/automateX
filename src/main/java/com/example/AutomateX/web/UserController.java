package com.example.AutomateX.web;

import com.example.AutomateX.service.user.UserService;

import com.example.AutomateX.web.dto.SignUpRequestDto;
import com.example.AutomateX.web.dto.SignUpResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user API", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  @Operation(summary = "회원 가입", description = "이름, 이메일, 비밀번호를 전달받아 회원가입을 진행한다. JWT 토큰 및 유저 정보를 반환한다.")
  @PostMapping("/join")
  public SignUpResponseDto signUp(@Valid @RequestBody SignUpRequestDto requestDto) {return userService.signUp(requestDto);}
}
