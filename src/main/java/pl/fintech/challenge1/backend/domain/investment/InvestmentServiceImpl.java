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
        Long payment = investitionParams.getFirstDeposit();
        Double profit = 0D;
        Double investmentValue = payment.doubleValue();
        graphData.getPayments().add(payment);
        graphData.getProfit().add(profit);
        graphData.getInvestmentValue().add(investmentValue);
        for(int i = 1; i <= investitionParams.getDurationInYears(); i++){
            payment = graphData.getPayments().get(graphData.getPayments().size() - 1)
                    + investitionParams.getSystematicPayments();

            Double current_profit = investitionParams.getReturnOnInvestment() / 100.0
                    * (graphData.getInvestmentValue().get(graphData.getInvestmentValue().size() - 1)
                    + investitionParams.getSystematicPayments());

            profit += current_profit;

            graphData.getPayments().add(payment);
            graphData.getProfit().add(profit);
            graphData.getInvestmentValue().add(payment + profit);
        }
        return graphData;
    }
}
