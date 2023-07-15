package com.example.AutomateX.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AnnualViolationCountDto {

    private String name;

    private int annualViolationCount;

    public AnnualViolationCountDto(String name, int annualViolationCount) {

        this.name = name;
        this.annualViolationCount = annualViolationCount;
    }
}
