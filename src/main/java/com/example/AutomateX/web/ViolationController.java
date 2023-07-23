package com.example.AutomateX.web;

import com.example.AutomateX.service.violation.ViolationService;
import com.example.AutomateX.web.dto.AnnualViolationCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/violation")
public class ViolationController {

    private final ViolationService violationService;

    @PostMapping("/annualViolation")
    public List<AnnualViolationCountDto> searchAnnualViolation(@RequestParam("port") String port) {

        return violationService.calculateAnnualViolationCount(port);
    }

    @PostMapping("/monthlyViolation")
    public List<Integer> searchMonthlyViolation(@RequestParam("pier") String pier) {

        return violationService.calculateMonthlyViolationCount(pier);
    }


    //pier로 매개변수로 필요
    @PostMapping("/newViolation")
    public List<String> searchNewViolation(@RequestParam("pier") String pier) {

        return violationService.searchNewViolation(pier);
    }
}
