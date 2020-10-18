package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.controller.dto.InvestitionParams;
import pl.fintech.challenge1.backend.domain.investment.GraphData;
import pl.fintech.challenge1.backend.domain.investment.InvestmentService;
import pl.fintech.challenge1.backend.domain.investment.Investment;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    //todo: tyle, że tutaj pewnie powinno może być dto aby nie musieć całego usera podawać, chyba że można to jedną
    // adnotacją zrobić
    public Investment createInvestment(@RequestBody Investment investment) {

        return investmentService.save(investment);
    }

    @PostMapping
    public List<Investment> getInvestition(@RequestBody InvestitionParams investitionParams){
        return investmentService.getInvestments(investitionParams);
    }

    @PostMapping("/profits")
    public List<GraphData> getProfits(@RequestBody List<Investment> investments) {
        return investmentService.getProfits(investments);
    }


}
