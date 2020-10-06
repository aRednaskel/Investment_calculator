package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fintech.challenge1.backend.controller.dto.InvestmentDto;
import pl.fintech.challenge1.backend.controller.mapper.InvestmentMapper;
import pl.fintech.challenge1.backend.domain.investment.InvestmentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/investment")
public class InvestmentController {

    private final InvestmentService investmentService;
    private final InvestmentMapper investmentMapper;

    @PostMapping
    public ResponseEntity<InvestmentDto> createInvestment(@RequestBody InvestmentDto investmentDto) {

        investmentService.save(investmentMapper.mapDTOToInvestment(investmentDto));

        return ResponseEntity.status(201).body(investmentDto);
    }

}
