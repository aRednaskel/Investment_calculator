package pl.fintech.challenge1.backend.domain.investment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

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
    public void shouldReturnCorrectValuesWithoutAdditionalDeposits(){
        InvestitionParams investitionParams = new InvestitionParams(1000L, 0L,
                2L, 10L, DepositFrequency.MONTH);
        GraphData graphData = investmentService.calculateInvestition(investitionParams);
        //length after 2 years of each list should be 3
        assertEquals(3, graphData.getPayments().size());
        assertEquals(3, graphData.getProfit().size());
        assertEquals(3, graphData.getInvestmentValue().size());

        assertEquals(Arrays.asList(1000L, 1000L, 1000L), graphData.getPayments());
        assertEquals(Arrays.asList(0d, 100d, 210d), graphData.getProfit());
        assertEquals(Arrays.asList(1000d, 1100d, 1210d), graphData.getInvestmentValue());
    }

    @Test
    public void shouldReturnCorrectValuesWithAdditionalDeposits(){
        InvestitionParams investitionParams = new InvestitionParams(1000L, 100L,
                2L, 10L, DepositFrequency.YEAR);
        GraphData graphData = investmentService.calculateInvestition(investitionParams);
        assertEquals(3, graphData.getPayments().size());
        assertEquals(3, graphData.getProfit().size());
        assertEquals(3, graphData.getInvestmentValue().size());

        assertEquals(Arrays.asList(1000L, 1100L, 1200L), graphData.getPayments());
        assertEquals(Arrays.asList(0d, 110d, 241d), graphData.getProfit());
        assertEquals(Arrays.asList(1000d, 1210d, 1441d), graphData.getInvestmentValue());
    }
}
