package com.example.AutomateX.domain.significant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Significant {

    @Id @GeneratedValue
    private Long id;

    private String arrivalScheduledTime;

    private String expectedVessel;

    private String callSign;

    private String vesselName;

    private String purposeOfArrival;

    private String cargoName;

    private int cargoTonnage;
}
