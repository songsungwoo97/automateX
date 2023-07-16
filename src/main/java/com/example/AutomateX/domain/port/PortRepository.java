package com.example.AutomateX.domain.port;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, Long> {

    Port findByName(String Name);
}
