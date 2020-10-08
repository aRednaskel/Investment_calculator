package pl.fintech.challenge1.backend.domain.investment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@RequiredArgsConstructor
class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;

    @Override
    public InvestmentType save(InvestmentType investmentType) {
        return investmentRepository.save(investmentType);
    }

    @Override
    public GraphData calculateInvestition(InvestitionParams investitionParams) {
        GraphData graphData = new GraphData(new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        Long payments = investitionParams.getFirstDeposit();
        Double profit = 0D;
        Double investmentValue = payments.doubleValue();
        graphData.getPayments().add(payments);
        graphData.getProfit().add(profit);
        graphData.getInvestmentValue().add(investmentValue);
        for(int i = 1; i <= investitionParams.getDurationInYears(); i++){
            payments += investitionParams.getFirstDeposit();
            //todo: niepoprwane ale na razie skupiamysie na programowaniu, potem popraw ten wzor
            profit *= (1+100.0/investitionParams.getReturnOnInvestment())*profit;

            graphData.getPayments().add(payments);
            graphData.getProfit().add(profit);
            graphData.getInvestmentValue().add(payments + profit);
        }
        return graphData;
    }
}
