package com.example.AutomateX.config.auth.dto;

import com.example.AutomateX.domain.user.Account;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
  private Account account;

  public SecurityUser(Account account) {
    super(account.getId().toString(), account.getPassword(),
        AuthorityUtils.createAuthorityList(account.getRole().toString()));
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }
}
