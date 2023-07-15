package com.example.AutomateX.domain.operator;

import com.example.AutomateX.domain.Pier;
import com.example.AutomateX.domain.Violation;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;


@Getter
@Entity
public class Operator {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pier> piers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Violation> violations;
}
