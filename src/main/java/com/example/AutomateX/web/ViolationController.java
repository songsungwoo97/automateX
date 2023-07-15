package com.example.AutomateX.web;

import com.example.AutomateX.service.ViolationService;
import com.example.AutomateX.web.dto.AnnualViolationCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/violation")
public class ViolationController {

    private final ViolationService violationService;

    @PostMapping("/AnnualViolation")
    public List<AnnualViolationCountDto> searchAnnualViolation() {

        return violationService.calculateAnnualViolationCount();
    }
}
