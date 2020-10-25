package pl.fintech.challenge1.backend.domain.investment;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.fintech.challenge1.backend.controller.dto.InvestitionParams;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final Logger log = LoggerFactory.getLogger(InvestmentServiceImpl.class);

    @Transactional
    @Override
    public Investment save(Investment investment) {
        if (investmentRepository.findByCompanyNameAndType(investment.getCompanyName(), investment.getType()).isEmpty()) {
            log.info("New investment was created with ");
            return investmentRepository.save(investment);
        }
        log.error("Investment with company name {} and type {} already exists", investment.getCompanyName(), investment.getType());
        throw new InvestmentAlreadyExistException("Investment already exists");
    }

    @Override
    public List<Investment> getInvestments(InvestitionParams investitionParams) {
        List<Investment> investments = investmentRepository.
                findByInitialCapitalLessThanEqual(investitionParams.getInitialCapital());
        investments = investments.stream()
                .filter( investment ->
                    investment.getDuration() <= investitionParams.getDuration() &&
                    investment.getAdditionalContribution().compareTo(investitionParams.getAdditionalContribution()) <= 0 &&
                    investment.getDepositFrequency().getNumberOfMonths() == investitionParams.getDepositFrequency().getNumberOfMonths() &&
                    investment.getReturnRate().compareTo(investitionParams.getReturnRate()) <= 0)
                .sorted(Comparator
                        .comparing(Investment::getInitialCapital)
                        .thenComparing(Investment::getReturnRate))
                .collect(Collectors.toList());
        return investments;
    }

    @Override
    public GraphData getSummary(List<Investment> investments) {
        List<BigDecimal> profits = new ArrayList<>();
        BigDecimal currentValue;
        BigDecimal initialCapital;
        double returnRate;
        BigDecimal additionalContribution;

        for (Investment investment: investments) {
            returnRate = investment.getReturnRate().doubleValue() / 100 / 12 + 1;
            initialCapital = investment.getInitialCapital();
            currentValue = initialCapital;
            additionalContribution = BigDecimal.ZERO;
            BigDecimal valueFromPreviousMonth;

            if (investment.getAdditionalContribution().intValue() == 0
                    || investment.getDepositFrequency().getNumberOfMonths() == 0) {
                for (int i = 1; i <= investment.getDuration() ; i++) {
                    valueFromPreviousMonth = currentValue;
                    currentValue = currentValue.multiply(
                            BigDecimal.valueOf(returnRate));
                    if (profits.size() >= i) {
                        profits.set(i - 1,
                                profits.get(i - 1)
                                .add(currentValue
                                        .subtract(valueFromPreviousMonth)));
                    } else {
                        profits.add(currentValue
                                .subtract(valueFromPreviousMonth));
                    }
                }
            } else {
                for (int i = 1; i <= investment.getDuration() ; i++) {
                    if ( i % investment.getDepositFrequency().getNumberOfMonths() == 0) {
                        currentValue = currentValue
                                .add(investment.getAdditionalContribution());
                        additionalContribution = additionalContribution
                                .add(investment.getAdditionalContribution());
                    }
                    valueFromPreviousMonth = currentValue;
                    currentValue = currentValue.multiply(
                            BigDecimal.valueOf(returnRate));
                    if (profits.size() >= i) {
                        profits.set(i - 1,
                                profits.get(i - 1)
                                        .add(currentValue
                                                .subtract(valueFromPreviousMonth)));
                    } else {
                        profits.add(currentValue
                                .subtract(valueFromPreviousMonth));
                    }
                }
            }
        }
        for (int i = 1; i < profits.size(); i++) {
            profits.set(i, profits.get(i)
                    .add(profits.get(i-1)));
        }
        return new GraphData(profits);
    }
}
