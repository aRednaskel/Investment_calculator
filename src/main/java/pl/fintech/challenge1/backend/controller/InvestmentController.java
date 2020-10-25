package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.controller.dto.InvestitionParams;
import pl.fintech.challenge1.backend.domain.investment.DepositFrequency;
import pl.fintech.challenge1.backend.domain.investment.GraphData;
import pl.fintech.challenge1.backend.domain.investment.Investment;
import pl.fintech.challenge1.backend.domain.investment.InvestmentService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Investment createInvestment(@Valid @RequestBody Investment investment) {
        return investmentService.save(investment);
    }

    @GetMapping
    public List<Investment> getInvestitions(@RequestParam BigDecimal initialCapital, @RequestParam Long duration,
                                            @RequestParam BigDecimal additionalContribution, @RequestParam DepositFrequency depositFrequency,
                                            @RequestParam BigDecimal returnRate){
        InvestitionParams investitionParams = new InvestitionParams(initialCapital, duration, additionalContribution, depositFrequency, returnRate);
        return investmentService.getInvestments(investitionParams);
    }

    @PostMapping("/return")
    public GraphData summaryOfAllInvestments(@Valid @RequestBody List<Investment> investments) {
        return investmentService.getSummary(investments);
    }


}
