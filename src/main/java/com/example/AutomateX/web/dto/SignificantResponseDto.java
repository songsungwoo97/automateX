package com.example.AutomateX.web.dto;


import com.example.AutomateX.domain.significant.Significant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class SignificantResponseDto {

    private LocalDateTime arrivalScheduledTime; //입항예정일시

    private String expectedVessel; //예상선석

    private String callSign; //호출부호

    private String vesselName; //선박명

    private String purposeOfArrival; //입항목적

    private String stevedore; //하역사

    private String cargoName; //화물명

    private int cargoTonnage; //화물 총 톤수

    public SignificantResponseDto(Significant significant) {
        this.arrivalScheduledTime = significant.getArrivalScheduledTime();
        this.expectedVessel = significant.getExpectedVessel();
        this.callSign = significant.getCallSign();
        this.vesselName = significant.getVesselName();
        this.purposeOfArrival = significant.getPurposeOfArrival();
        this.stevedore = significant.getStevedore();
        this.cargoName = significant.getCargoName();
        this.cargoTonnage = significant.getCargoTonnage();
    }
}
