package com.example.AutomateX.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AnnualViolationCountDto {

    @Schema(description = "운영사명")
    private String name;

    @Schema(description = "연간 위반건수")
    private int annualViolationCount;

    public AnnualViolationCountDto(String name, int annualViolationCount) {

        this.name = name;
        this.annualViolationCount = annualViolationCount;
    }
}
