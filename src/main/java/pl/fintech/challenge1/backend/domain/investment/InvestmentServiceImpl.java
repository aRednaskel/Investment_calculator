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
        //nie musze tu wpisywac double w diamencie bo java sama się domyśli skoro tam są double
        GraphData graphData = new GraphData(new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        // powinienem korzystać z klasy double bo jeśli korzystam ze zmiennej typu double i autoboxingu to jest to
        // dodatkowa operacja
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
