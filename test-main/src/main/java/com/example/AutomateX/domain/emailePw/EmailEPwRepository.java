package com.example.AutomateX.domain.emailePw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailEPwRepository extends JpaRepository<EmailEPw, Long> {

    Optional<EmailEPw> findByEmail(String email);
}

