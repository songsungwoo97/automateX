package com.example.AutomateX.domain;

import com.example.AutomateX.domain.operator.Operator;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Pier {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Operator operator;
}
