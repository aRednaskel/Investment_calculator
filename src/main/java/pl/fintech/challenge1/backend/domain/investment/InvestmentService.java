package pl.fintech.challenge1.backend.domain.investment;

import pl.fintech.challenge1.backend.controller.dto.InvestitionParams;

import java.util.List;

public interface InvestmentService {
    Investment save(Investment investment);

    List<Investment> getInvestments(InvestitionParams investitionParams);

    GraphData getSummary(List<Investment> investments);
}
