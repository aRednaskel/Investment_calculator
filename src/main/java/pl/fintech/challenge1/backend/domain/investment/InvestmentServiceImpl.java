package pl.fintech.challenge1.backend.domain.investment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fintech.challenge1.backend.controller.dto.InvestitionParams;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;

    @Override
    public Investment save(Investment investment) {
        return investmentRepository.save(investment);
    }

    @Override
    public List<Investment> getInvestments(InvestitionParams investitionParams) {
            List<Investment> investments = investmentRepository
                    .findByInitialCapitalGreaterThanEqualAndReturnRateLessThanEqual(investitionParams.getInitialCapital(), investitionParams.getReturnRate());
        return investments;
    }

    @Override
    public List<Map<Integer, BigDecimal>> getProfits(List<Investment> investments) {
        List<Map<Integer, BigDecimal>> profitList = new LinkedList<>();
        Map<Integer, BigDecimal> profitMap = new HashMap<>();
        for (Investment investment: investments) {
            for (int i = 1; i <= investment.getDuration() ; i++) {
                profitMap.put(i, BigDecimal.valueOf(
                   Math.pow(investment.getReturnRate().doubleValue(), i) * investment.getInitialCapital()
                ));
            }
            profitList.add(profitMap);
            profitMap.clear();
        }
        return profitList;
    }
}
