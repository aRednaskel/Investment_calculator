package pl.fintech.challenge1.backend.domain.investment

import pl.fintech.challenge1.backend.controller.dto.InvestitionParams
import spock.lang.Specification

class InvestmentServiceImplTest extends Specification {

    def investmentRepository = Mock(InvestmentRepository)
    def investmentService = new InvestmentServiceImpl(investmentRepository)

    def "GetInvestments"() {
        given:
            List<Investment> investments = createListOfInvestments(10)
        investmentRepository.
                findByInitialCapitalLessThanEqual(BigDecimal.valueOf(9000)) >> investments
        when:
        investments = investmentService.getInvestments(getInvestitionParams())
        then:
        assert investments.size() == 6
        investments.each {
            assert it.getInitialCapital() <= getInvestitionParams().getInitialCapital()
            assert it.getDuration() <= getInvestitionParams().getDuration()
            assert it.getAdditionalContribution() <= getInvestitionParams().getAdditionalContribution()
            assert it.getDepositFrequency() == getInvestitionParams().getDepositFrequency()
            assert it.getReturnRate() <= getInvestitionParams().getReturnRate()
        }

    }

    def "Summary"() {
        when:
        GraphData profits = investmentService.getSummary(createListOfInvestments(10))
        def profit = [47, 95, 142, 190, 238, 285, 331, 374, 414, 447, 471, 485]
        def profitSize = profits.getProfits().size()
        then:
        assert profits.getMonths().size() == profitSize
        for (i in 0..< profitSize) {
            assert Math.floor(profits.getProfits().get(i)) == profit[i]
        }
    }


    List<Investment> createListOfInvestments(int number) {
        List<Investment> investments = new ArrayList<>();
        for (i in 1..<number) {
            investments.add(Investment.builder()
                    .id(i)
                    .companyName("Company")
                    .logoUrl("Logo")
                    .type(Type.BONDS)
                    .initialCapital(BigDecimal.valueOf(1000l * 2))
                    .duration(3 + i)
                    .additionalContribution(BigDecimal.valueOf(50 * (i % 2)))
                    .depositFrequency(DepositFrequency.QUARTER)
                    .returnRate(BigDecimal.valueOf(0.2 * i)).build())
        }
        return investments;
    }

    InvestitionParams getInvestitionParams() {
        return new InvestitionParams(BigDecimal.valueOf(9000), 9,
                BigDecimal.valueOf(50), DepositFrequency.QUARTER, BigDecimal.TEN)
    }
}
