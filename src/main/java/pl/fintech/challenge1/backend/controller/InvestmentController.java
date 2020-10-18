package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.controller.dto.InvestitionParams;
import pl.fintech.challenge1.backend.domain.investment.GraphData;
import pl.fintech.challenge1.backend.domain.investment.Investment;
import pl.fintech.challenge1.backend.domain.investment.InvestmentService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Investment createInvestment(@Valid @RequestBody Investment investment) {

        return investmentService.save(investment);
    }

    @PostMapping
    public List<Investment> getInvestition(@Valid @RequestBody InvestitionParams investitionParams){
        return investmentService.getInvestments(investitionParams);
    }

    @PostMapping("/profits")
    public List<GraphData> getProfits(@Valid @RequestBody List<Investment> investments) {
        return investmentService.getProfits(investments);
    }


}
