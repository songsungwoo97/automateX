package com.example.AutomateX.service;

import com.example.AutomateX.domain.operator.Operator;
import com.example.AutomateX.domain.operator.OperatorRepository;
import com.example.AutomateX.domain.pier.Pier;
import com.example.AutomateX.domain.pier.PierRepository;
import com.example.AutomateX.domain.port.Port;
import com.example.AutomateX.domain.port.PortRepository;
import com.example.AutomateX.domain.violation.Violation;
import com.example.AutomateX.domain.violation.ViolationRepository;
import com.example.AutomateX.web.dto.AnnualViolationCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ViolationService {

    private final PortRepository portRepository;
    private final OperatorRepository operatorRepository;
    private final ViolationRepository violationRepository;

    public List<AnnualViolationCountDto> calculateAnnualViolationCount(String portName) { //운영사 별 위반건수

        //ex) 울산 본항
        Port port = portRepository.findByName(portName);

        List<AnnualViolationCountDto> annualViolationCountForms = new ArrayList<>();

        //ex) 울산 본항 안에 있는 모든 운영사
        List<Operator> operators = port.getOperators();

        //운영사 별 위반 건수
        for (Operator op : operators) {
            String name = op.getName();
            int violationCnt = op.getViolations().size();

            AnnualViolationCountDto form = new AnnualViolationCountDto(name, violationCnt);
            annualViolationCountForms.add(form);
        }

        return annualViolationCountForms;
    }


    public List<Integer> calculateMonthlyViolationCount(String pierName) {

        List<Violation> violations = violationRepository.findAllByPier(pierName);

        int[] monthlyCounts = new int[12];

        for (Violation violation : violations) {
            int month = violation.getDateTime().getMonthValue() - 1; // MonthValue는 1부터 시작하므로 0부터 시작하게 -1을 해줌
            monthlyCounts[month]++;
        }
        return Arrays.stream(monthlyCounts).boxed().collect(Collectors.toList());
    }
}


