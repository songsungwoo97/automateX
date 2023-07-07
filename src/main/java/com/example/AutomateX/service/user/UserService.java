package com.example.AutomateX.service.user;

import com.example.AutomateX.config.auth.JwtTokenProvider;
import com.example.AutomateX.config.auth.dto.SearchUser;
import com.example.AutomateX.domain.user.Account;
import com.example.AutomateX.domain.user.UserRepository;

import com.example.AutomateX.web.dto.LoginRequestDto;
import com.example.AutomateX.web.dto.LoginResponseDto;
import com.example.AutomateX.web.dto.SignUpRequestDto;

import com.example.AutomateX.web.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;


  //회원가입
  @Transactional
  public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

    if (userRepository.existsByEmail(requestDto.getEmail())) {
      throw new IllegalArgumentException("이메일이 이미 존재합니다.");
    }

    requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
    Account account = requestDto.toEntity();

    this.userRepository.save(account);
    return new SignUpResponseDto(new SearchUser(account), jwtTokenProvider.createAccessToken(account.getId(), account.getRole()));
  }

  //로그인
  public LoginResponseDto login(LoginRequestDto requestDto) {

    String email = requestDto.getEmail();
    String password = requestDto.getPassword();

    Optional<Account> optionalAccount = userRepository.findByEmail(email); //이메일 확인
    if (!optionalAccount.isPresent()) {
      throw new NotFoundException("이메일이 틀렸습니다.");
    }

    Account account = optionalAccount.get();

    if (!passwordEncoder.matches(password, account.getPassword())) { //패스워드 확인
      throw new BadCredentialsException("비밀번호가 틀렸습니다.");
    }

    String jwtToken = jwtTokenProvider.createAccessToken(account.getId(), account.getRole());

    return new LoginResponseDto(new SearchUser(account), jwtToken);
  }
}