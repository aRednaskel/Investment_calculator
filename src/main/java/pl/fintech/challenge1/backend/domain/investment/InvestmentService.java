package pl.fintech.challenge1.backend.domain.investment;

import pl.fintech.challenge1.backend.controller.dto.InvestitionParams;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface InvestmentService {
    Investment save(Investment investment);

    List<Investment> getInvestments(InvestitionParams investitionParams);

    List<Map<Integer, BigDecimal>> getProfits(List<Investment> investments);
}
