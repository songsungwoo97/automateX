package com.example.AutomateX.domain.violation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation, Long> {

    List<Violation> findAllByPier(String pier);
}
