package com.example.AutomateX.service;

import com.example.AutomateX.domain.operator.Operator;
import com.example.AutomateX.domain.operator.OperatorRepository;
import com.example.AutomateX.web.dto.AnnualViolationCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ViolationService {

    private final OperatorRepository operatorRepository;

    @Transactional
    public List<AnnualViolationCountDto> calculateAnnualViolationCount() { //운영사 별 위반건수

        List<AnnualViolationCountDto> annualViolationCountForms = new ArrayList<>();

        List<Operator> operators = operatorRepository.findAll();

        for(Operator op : operators) {
            String name = op.getName();
            int violationCnt = op.getViolations().size();

            AnnualViolationCountDto form = new AnnualViolationCountDto(name, violationCnt);
            annualViolationCountForms.add(form);
        }

        return annualViolationCountForms;
    }

}


