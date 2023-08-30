package com.example.AutomateX.domain.significant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SignificantRepository extends JpaRepository<Significant, Long> {

    @Query("SELECT s FROM Significant s WHERE s.arrivalScheduledTime BETWEEN :start AND :end AND s.port.name = :port AND s.pier.name = :pier")
    List<Significant> findSignificants(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("port") String port, @Param("pier") String pier);

}