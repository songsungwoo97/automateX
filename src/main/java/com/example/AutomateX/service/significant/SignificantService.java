package com.example.AutomateX.service.significant;

import com.example.AutomateX.domain.significant.Significant;
import com.example.AutomateX.domain.significant.SignificantRepository;
import com.example.AutomateX.web.dto.SignificantRequestDto;
import com.example.AutomateX.web.dto.SignificantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SignificantService {

    private final SignificantRepository significantRepository;

    public List<SignificantResponseDto> searchSignificantList(SignificantRequestDto requestDto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(requestDto.getStart(), formatter);
        LocalDateTime startDateTime = startDate.atStartOfDay();

        LocalDate endDate = LocalDate.parse(requestDto.getEnd(), formatter);
        LocalDateTime endDateTime = endDate.atStartOfDay();

        //LocalDate start = LocalDate.parse(requestDto.getStart());
        //LocalDate end = LocalDate.parse(requestDto.getEnd());

        String port = requestDto.getPort();
        String pier = requestDto.getPier();

        List<Significant> significants = significantRepository.findSignificants(startDateTime, endDateTime, port, pier);

        return significants.stream()
                .map(SignificantResponseDto::new)
                .collect(Collectors.toList());
    }
}
