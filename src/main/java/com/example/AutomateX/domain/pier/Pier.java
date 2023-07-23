package com.example.AutomateX.domain.pier;

import com.example.AutomateX.domain.operator.Operator;
import com.example.AutomateX.domain.violation.Violation;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Pier {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Violation> violations = new ArrayList<>();

    public Pier(String name) {
        this.name = name;
    }

    public Pier() {}
}
