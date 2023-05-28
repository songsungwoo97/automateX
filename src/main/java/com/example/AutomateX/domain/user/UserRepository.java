package com.example.AutomateX.domain.user;

import com.example.AutomateX.config.auth.dto.SearchUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<Account, UUID> {
  Optional<Account> findByEmail(String email);
}