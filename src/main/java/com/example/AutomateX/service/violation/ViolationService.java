package com.example.AutomateX.service.violation;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ViolationService {

    private final PortRepository portRepository;
    private final PierRepository pierRepository;
    private final ViolationRepository violationRepository;

    public List<AnnualViolationCountDto> calculateAnnualViolationCount(String portName) { //운영사 별 위반건수

        //ex) 울산 본항
        Port port = portRepository.findByName(portName);

        List<AnnualViolationCountDto> annualViolationCountForms = new ArrayList<>();

        //ex) 울산 본항 안에 있는 모든 운영사
        List<Operator> operators = port.getOperators();

        int year = LocalDate.now().getYear();

        //운영사 별 위반 건수
        for (Operator op : operators) {
            String name = op.getName();
            //이번 년도의 통계만 카운트
            int violationCnt = (int) op.getViolations().stream()
                    .filter(violation -> violation.getDateTime().getYear() == year)
                    .count();
            AnnualViolationCountDto form = new AnnualViolationCountDto(name, violationCnt);
            annualViolationCountForms.add(form);
        }

        return annualViolationCountForms;
    }


    public List<Integer> calculateMonthlyViolationCount(String pierName) {

        int year = LocalDate.now().getYear();
        Pier pier = pierRepository.findByName(pierName);
        List<Violation> violations = violationRepository.findByPierAndYear(pier, year);

        int[] monthlyCounts = new int[12];

        for (Violation violation : violations) {
            int month = violation.getDateTime().getMonthValue() - 1; // 월은 1부터 시작하므로 0부터 시작하게 -1을 해줌
            monthlyCounts[month]++;
        }
        return Arrays.stream(monthlyCounts).boxed().collect(Collectors.toList()); //배열을 리스트로 변환
    }

    public List<String> searchNewViolation(String pierName) {

        Pier pier = pierRepository.findByName(pierName);

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        List<Violation> violations = violationRepository.findByPierAndYearAndMonth(pier, year, month);
        return violations.stream()
                .map(Violation::getViolationType)
                .collect(Collectors.toList());
    }
}


