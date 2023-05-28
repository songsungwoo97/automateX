package com.example.AutomateX.config.auth;

import java.util.Optional;

import com.example.AutomateX.config.auth.dto.SecurityUser;
import com.example.AutomateX.domain.user.Account;
import com.example.AutomateX.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Account> optional = userRepository.findByEmail(username);
    if(!optional.isPresent()) {
      throw new UsernameNotFoundException(username + " 사용자 없음");
    } else {
      Account account = optional.get();
      return new SecurityUser(account);
    }
  }
}