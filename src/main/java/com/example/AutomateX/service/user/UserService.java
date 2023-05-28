package com.example.AutomateX.service.user;

import com.example.AutomateX.config.auth.JwtTokenProvider;
import com.example.AutomateX.config.auth.dto.SearchUser;
import com.example.AutomateX.domain.user.Account;
import com.example.AutomateX.domain.user.UserRepository;

import com.example.AutomateX.web.dto.SignUpRequestDto;

import com.example.AutomateX.web.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;


  @Transactional
  public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
    requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
    Account account = requestDto.toEntity();
    this.userRepository.save(account);
    return new SignUpResponseDto(new SearchUser(account), jwtTokenProvider.createAccessToken(account.getId(), account.getRole()));
  }
}