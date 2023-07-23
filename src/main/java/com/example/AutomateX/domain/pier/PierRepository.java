package com.example.AutomateX.domain.pier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PierRepository extends JpaRepository<Pier, Long> {

    Pier findByName(String name);
}
