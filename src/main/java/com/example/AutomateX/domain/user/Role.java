package com.example.AutomateX.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

  USER("ROLE_USER", "일반 사용자", false),
  ADMIN("ROLE_ADMIN", "어드민", true),
  울산항만공사("ROLE_ULHMC", "울산항만공사", true),
  신흥사("ROLE_OPERATOR1", "신흥사", false),
  고려항만("ROLE_OPERATOR2", "고려항만", false),
  훙산항만운영("ROLE_OPERATOR3", "훙산항만운영", false),
  한국보팔터미날("ROLE_OPERATOR4", "한국보팔터미날", false);

  private final String key;
  private final String title;
  private final boolean canViewAllInfo; //모든 열람 권한 = true
}