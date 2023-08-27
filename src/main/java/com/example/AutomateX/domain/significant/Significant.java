package com.example.AutomateX.domain.significant;

import com.example.AutomateX.domain.pier.Pier;
import com.example.AutomateX.domain.port.Port;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Significant {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime arrivalScheduledTime; //입항예정일시

    private String expectedVessel; //예상선석

    private String callSign; //호출부호

    private String vesselName; //선박명

    private String purposeOfArrival; //입항목적

    private String stevedore; //하역사

    private String cargoName; //화물명

    private int cargoTonnage; //화물 총 톤수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "port_id")
    private Port port;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pier_id")
    private Pier pier;

    public Significant(LocalDateTime arrivalScheduledTime, String expectedVessel, String callSign, String vesselName, String purposeOfArrival, String stevedore, String cargoName, int cargoTonnage) {

        this.arrivalScheduledTime = arrivalScheduledTime;
        this.expectedVessel = expectedVessel;
        this.callSign = callSign;
        this.vesselName = vesselName;
        this.purposeOfArrival = purposeOfArrival;
        this.stevedore = stevedore;
        this.cargoName = cargoName;
        this.cargoTonnage = cargoTonnage;
    }

    public Significant() {

    }
}
