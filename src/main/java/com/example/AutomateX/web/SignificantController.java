package com.example.AutomateX.web;

import com.example.AutomateX.domain.significant.Significant;
import com.example.AutomateX.service.significant.SignificantService;
import com.example.AutomateX.web.dto.SignificantRequestDto;
import com.example.AutomateX.web.dto.SignificantResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Significant API", description = "특이사항 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/significant")
public class SignificantController {

    private final SignificantService significantService;

   @PostMapping("/significantlist")
    public List<SignificantResponseDto> getSignificantList(@RequestBody SignificantRequestDto requestDto) {

        return significantService.searchSignificantList(requestDto);
    }


}
