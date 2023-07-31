package com.example.AutomateX.service.significant;

import com.example.AutomateX.domain.significant.SignificantRepository;
import com.example.AutomateX.web.dto.SignificantRequestDto;
import com.example.AutomateX.web.dto.SignificantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SignificantService {

    private final SignificantRepository significantRepository;

/*    public List<SignificantResponseDto> searchSignificantList(SignificantRequestDto requestDto) {

        
    }
 */
}
