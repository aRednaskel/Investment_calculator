package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.domain.investment.GraphData;
import pl.fintech.challenge1.backend.domain.investment.InvestitionParams;
import pl.fintech.challenge1.backend.domain.investment.InvestmentService;
import pl.fintech.challenge1.backend.domain.investment.InvestmentType;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //todo: tyle, że tutaj pewnie powinno może być dto aby nie musieć całego usera podawać, chyba że można to jedną
    // adnotacją zrobić
    public InvestmentType createInvestment(@RequestBody InvestmentType investmentType) {

        return investmentService.save(investmentType);
    }

    @PostMapping("/investition")
    public GraphData getInvestition(@RequestBody InvestitionParams investitionParams){
        return investmentService.calculateInvestition(investitionParams);
    }

}
