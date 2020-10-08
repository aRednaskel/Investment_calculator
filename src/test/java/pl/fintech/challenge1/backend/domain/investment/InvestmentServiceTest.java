package pl.fintech.challenge1.backend.domain.investment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvestmentServiceTest {

    @Autowired
    InvestmentService investmentService;

    @Test
    public void shouldReturnLengthOne(){
        InvestitionParams investitionParams = new InvestitionParams(1000L, 100L,
                0L, 5L, DepositFrequency.MONTH);
        GraphData graphData = investmentService.calculateInvestition(investitionParams);

    }
}
