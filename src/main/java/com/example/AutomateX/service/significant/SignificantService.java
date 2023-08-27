package com.example.AutomateX.service.significant;

import com.example.AutomateX.domain.significant.Significant;
import com.example.AutomateX.domain.significant.SignificantRepository;
import com.example.AutomateX.web.dto.SignificantRequestDto;
import com.example.AutomateX.web.dto.SignificantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        String tmp_start = requestDto.getStart();
        String tmp_end = requestDto.getEnd();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime start = LocalDateTime.parse(tmp_start, formatter);
        LocalDateTime end = LocalDateTime.parse(tmp_end, formatter);

        String port = requestDto.getPort();
        String pier = requestDto.getPier();

        List<Significant> significants = significantRepository.findSignificants(start, end, port, pier);

        return significants.stream()
                .map(SignificantResponseDto::new)
                .collect(Collectors.toList());
    }
}
