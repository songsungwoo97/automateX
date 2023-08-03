package com.example.AutomateX.web;

import com.example.AutomateX.service.mail.MailService;
import com.example.AutomateX.service.user.UserService;

import com.example.AutomateX.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Tag(name = "user API", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  private final UserService userService;
  private final MailService mailService;

  @Operation(summary = "회원 가입", description = "이름, 이메일, 비밀번호, 인증번호를 전달받아 회원가입을 진행한다. JWT 토큰 및 유저 정보를 반환한다.")
  @PostMapping("/join")
  public SignUpResponseDto signUp(@RequestBody @Valid SignUpRequestDto requestDto) {
    return userService.signUp(requestDto);
  }

  @Operation(summary = "인증번호 전송", description = "입력받은 이메일로 인증번호를 전송한다.")
  //이메일에 인증번호를 전송
  @GetMapping("/email")
  public String sendMail(@RequestBody String email, HttpSession session) throws MessagingException, UnsupportedEncodingException {

    String verificationEPw = mailService.sendMessage(email);
    session.setMaxInactiveInterval(3 * 60); //3분 후에 세션 만료
    session.setAttribute("verificationEPw", verificationEPw);
    return verificationEPw;
  }

  //인증번호를 체크
  @Operation(summary = "인증번호 검증", description = "이메일로 전송받은 인증번호를 검증한다.")
  @PostMapping("/verify")
  public boolean verifyEPw(@RequestBody EPwRequest request, HttpSession session) {
    String sessionEPw = (String) session.getAttribute("verificationEPw");

    if (sessionEPw == null) {// 3분이 지나면 세션만료
      return false;
    }
    if(request.getEPw().equals(sessionEPw)) {
      session.invalidate();
      return true;
    }
    else {
      return false;
    }
  }

  //로그인 구현
  @Operation(summary = "로그인", description = "이메일, 비밀번호를 전달받아 로그인을 진행한다. JWT 토큰 및 유저 정보를 반환한다.")
  @PostMapping("/login")
  public LoginResponseDto login(@RequestBody @Valid LoginRequestDto requestDto) {
    return userService.login(requestDto);
  }

}

