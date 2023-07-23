package com.example.AutomateX.domain.violation;

import com.example.AutomateX.domain.operator.Operator;
import com.example.AutomateX.domain.pier.Pier;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
public class Violation {

    @Id @GeneratedValue
    private Long id;

    //private String name;

    private String violationType;

    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Operator operator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pier_id")
    private Pier pier;

    public Violation(String violationType, LocalDateTime dateTime, Operator operator, Pier pier) {

        this.violationType = violationType;
        this.dateTime = dateTime;
        this.operator = operator;
        this.pier = pier;
    }

    public Violation() {}
}
