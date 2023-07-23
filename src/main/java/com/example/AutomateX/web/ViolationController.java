package com.example.AutomateX.web;

import com.example.AutomateX.service.violation.ViolationService;
import com.example.AutomateX.web.dto.AnnualViolationCountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "violation API", description = "위반사항 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/violation")
public class ViolationController {

    private final ViolationService violationService;

    @Operation(summary = "운영사들의 위반사항 수", description = "항구의 이름을 받아 올해 운영사별 위반건수를 반환한다.")
    @PostMapping("/annualViolation")
    public List<AnnualViolationCountDto> searchAnnualViolation(@RequestParam("port") String port) {

        return violationService.calculateAnnualViolationCount(port);
    }

    @Operation(summary = "월별 부두의 위반사항 수", description = "부두의 이름을 받아 이번 년도의 월별 위반사항 건수를 반환한다.")
    @PostMapping("/monthlyViolation")
    public List<Integer> searchMonthlyViolation(@RequestParam("pier") String pier) {

        return violationService.calculateMonthlyViolationCount(pier);
    }


    //pier로 매개변수로 필요
    @Operation(summary = "이번 달 위반사항 종류들", description = "부두의 이름을 전달받고 그 부두의 이번 달 위반사항 종류들을 반환한다.")
    @PostMapping("/newViolation")
    public List<String> searchNewViolation(@RequestParam("pier") String pier) {

        return violationService.searchNewViolation(pier);
    }
}
