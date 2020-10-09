package pl.fintech.challenge1.backend.domain.investment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvestmentServiceTest {

    @Autowired
    InvestmentService investmentService;

    //I use this calculator to get values for tests:
    //https://investors.pl/kalkulator-inwestycyjny/
    //but any investment calculator will do fine
    @Test
    public void shouldReturnLengthOne(){
        InvestitionParams investitionParams = new InvestitionParams(0L, 0L,
                0L, 5L, DepositFrequency.MONTH);
        GraphData graphData = investmentService.calculateInvestition(investitionParams);
        assertEquals(1, graphData.getPayments().size());
        assertEquals(1, graphData.getProfit().size());
        assertEquals(1, graphData.getInvestmentValue().size());

        assertEquals(0, graphData.getPayments().get(0));
        assertEquals(0, graphData.getProfit().get(0));
        assertEquals(0, graphData.getInvestmentValue().get(0));
    }
}
