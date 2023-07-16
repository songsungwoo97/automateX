package com.example.AutomateX.service;

import com.example.AutomateX.domain.operator.Operator;
import com.example.AutomateX.domain.operator.OperatorRepository;
import com.example.AutomateX.domain.port.Port;
import com.example.AutomateX.domain.port.PortRepository;
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

    private final PortRepository portRepository;
    private final OperatorRepository operatorRepository;

    @Transactional
    public List<AnnualViolationCountDto> calculateAnnualViolationCount(String portName) { //운영사 별 위반건수

        //ex) 울산 본항
        Port port = portRepository.findByName(portName);

        List<AnnualViolationCountDto> annualViolationCountForms = new ArrayList<>();

        //ex) 울산 본항 안에 있는 모든 운영사
        List<Operator> operators = port.getOperators();

        //운영사 별 위반 건수
        for(Operator op : operators) {
            String name = op.getName();
            int violationCnt = op.getViolations().size();

            AnnualViolationCountDto form = new AnnualViolationCountDto(name, violationCnt);
            annualViolationCountForms.add(form);
        }

        return annualViolationCountForms;
    }

}


