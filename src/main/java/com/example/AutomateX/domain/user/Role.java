package com.example.AutomateX.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

  USER("ROLE_USER", "일반 사용자", false),
  ADMIN("ROLE_ADMIN", "어드민", true),
  ULHMC("ROLE_ULHMC", "울산항만공사", true),
  OPERATOR1("ROLE_OPERATOR1", "신흥사", false),
  OPERATOR2("ROLE_OPERATOR2", "고려항만", false),
  OPERATOR3("ROLE_OPERATOR3", "훙산항만운영", false),
  OPERATOR4("ROLE_OPERATOR4", "한국보팔터미날", false);

  private final String key;
  private final String title;
  private final boolean canViewAllInfo; //모든 열람 권한 = true

}