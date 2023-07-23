package com.example.AutomateX.domain.violation;

import com.example.AutomateX.domain.pier.Pier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation, Long> {

    @Query("SELECT v FROM Violation v WHERE v.pier = :pier AND YEAR(v.dateTime) = :year")
    List<Violation> findByPierAndYear(@Param("pier") Pier pier, @Param("year") int year);

    //년도와 달을 통해 반환
    @Query("SELECT v FROM Violation v WHERE v.pier = :pier AND YEAR(v.dateTime) = :year AND MONTH(v.dateTime) = :month")
    List<Violation> findByPierAndYearAndMonth(@Param("pier") Pier pier, @Param("year") int year, @Param("month") int month);
}
