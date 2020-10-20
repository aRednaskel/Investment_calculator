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
                findByInitialCapitalGreaterThanEqual(BigDecimal.valueOf(1000)) >> investments
        when:
        investments = investmentService.getInvestments(getInvestitionParams())
        then:
        assert investments.size() == 5
        investments.each {
            assert it.getInitialCapital().compareTo(1000) >= 0
            assert it.getReturnRate().compareTo(1) >= 0
        }

    }

    def "GetProfits"() {
        when:
        List<GraphData> profitList = investmentService.getProfits(createListOfInvestments(10))
        then:
        assert profitList.size() == 9
        profitList.each {
            def profitSize = it.getProfits().size()
            assert profitSize == it.getMonths().get(profitSize -  1)
            assert Math.pow( (1 + 0.2 * profitSize / 100 / 12), profitSize) <= it.getProfits().size() - 1
        }
    }

    def "GetSummary"() {
        when:
        GraphData profits = investmentService.getSummary(createListOfInvestments(10))
        def profitSize = profits.getProfits().size()
        then:
        assert profits.getMonths().size() == profitSize
        assert Math.pow( (1 + 0.2 * profitSize / 100 / 12), profitSize) <= profits.getProfits().size() - 1

    }


    List<Investment> createListOfInvestments(int number) {
        List<Investment> investments = new ArrayList<>();
        for (i in 1..<number) {
            investments.add(Investment.builder()
                    .id(i)
                    .companyName("")
                    .logoUrl("")
                    .type(Type.BONDS)
                    .initialCapital(BigDecimal.valueOf(1000l * i))
                    .duration(3 + i)
                    .additionalContribution(BigDecimal.valueOf(50 * (i % 2)))
                    .depositFrequency(DepositFrequency.QUARTER)
                    .returnRate(BigDecimal.valueOf(0.2 * i)).build())
        }
        return investments;
    }

    InvestitionParams getInvestitionParams() {
        return new InvestitionParams(BigDecimal.valueOf(1000), 5,
                BigDecimal.valueOf(50), DepositFrequency.QUARTER, BigDecimal.ONE)
    }
}
