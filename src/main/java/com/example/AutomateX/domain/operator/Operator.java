package com.example.AutomateX.domain.operator;

import com.example.AutomateX.domain.pier.Pier;
import com.example.AutomateX.domain.violation.Violation;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
public class Operator {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pier> piers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Violation> violations = new ArrayList<>();

    public Operator(String name) {
        this.name = name;
    }

    public Operator() {}
}
