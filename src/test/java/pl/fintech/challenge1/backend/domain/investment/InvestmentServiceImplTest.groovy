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
        investments.size() == 5
    }



    List<Investment> createListOfInvestments(int number) {
        List<Investment> investments = new ArrayList<>();
        for (i in 1..<number) {
            investments.add(Investment.builder().id(i).companyName("").logoUrl("").type(Type.BONDS)
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
