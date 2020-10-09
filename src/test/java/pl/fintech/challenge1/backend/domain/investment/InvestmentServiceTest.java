package pl.fintech.challenge1.backend.domain.investment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

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

    @Test
    public void shouldReturnCorrectValuesWithoutAdditionalContributions(){
        InvestitionParams investitionParams = new InvestitionParams(1000L, 0L,
                2L, 10L, DepositFrequency.MONTH);
        GraphData graphData = investmentService.calculateInvestition(investitionParams);
        //length after 2 years of each list should be 3
        assertEquals(3, graphData.getPayments().size());
        assertEquals(3, graphData.getProfit().size());
        assertEquals(3, graphData.getInvestmentValue().size());

        assertEquals(Arrays.asList(1000L, 2000L, 3000L), graphData.getPayments());
        assertEquals(Arrays.asList(0d, 100d, 210d), graphData.getProfit().size());
        assertEquals(Arrays.asList(1000d, 2100d, 3210d), graphData.getInvestmentValue().size());
    }
}
