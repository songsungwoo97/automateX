package com.example.AutomateX.domain.port;


import com.example.AutomateX.domain.operator.Operator;
import com.example.AutomateX.domain.pier.Pier;
import com.example.AutomateX.domain.significant.Significant;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Port {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Operator> operators = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Significant> significants = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pier> piers = new ArrayList<>();

    public Port(String name) {
        this.name = name;
    }

    public Port() {}
}
