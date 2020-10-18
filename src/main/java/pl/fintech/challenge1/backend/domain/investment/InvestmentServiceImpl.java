package pl.fintech.challenge1.backend.domain.investment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fintech.challenge1.backend.controller.dto.InvestitionParams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public List<GraphData> getProfits(List<Investment> investments) {
        List<GraphData> profitList = new ArrayList<>();
        BigDecimal currentValue;
        long initialCapital;
        GraphData graphData;
        double returnRate;
        BigDecimal additionalContribution;

        for (Investment investment: investments) {
            returnRate = investment.getReturnRate().doubleValue() / 100 + 1;
            graphData = new GraphData(investment.getDuration().intValue());
            initialCapital = investment.getInitialCapital();
            currentValue = BigDecimal.valueOf(initialCapital);
            additionalContribution = BigDecimal.valueOf(investment.getAdditionalContribution());

            if (investment.getAdditionalContribution() == 0) {
                for (int i = 1; i <= investment.getDuration() ; i++) {
                    currentValue = currentValue.multiply(
                            BigDecimal.valueOf(returnRate));
                    graphData.addNextMonthData(i,
                            currentValue.subtract(BigDecimal.valueOf(initialCapital)));
                }
            } else {
                for (int i = 1; i <= investment.getDuration() ; i++) {
                    currentValue = currentValue.multiply(
                            BigDecimal.valueOf(returnRate));
                    graphData.addNextMonthData(i,
                            currentValue.subtract(BigDecimal.valueOf(initialCapital)));
                    if ( i % investment.getDepositFrequency().getNumberOfMonths() == 0)
                        currentValue = currentValue.add(additionalContribution);
                }
            }
            profitList.add(graphData);
        }
        return profitList;
    }
}
