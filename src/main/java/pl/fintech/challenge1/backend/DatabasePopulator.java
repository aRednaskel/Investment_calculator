package pl.fintech.challenge1.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fintech.challenge1.backend.domain.investment.DepositFrequency;
import pl.fintech.challenge1.backend.domain.investment.Investment;
import pl.fintech.challenge1.backend.domain.investment.InvestmentService;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements CommandLineRunner {

    private final InvestmentService investmentService;

    @Override
    public void run(String... args) throws Exception {
        investmentService.save(Investment.builder().id(1)
                .companyName("google")
                .logoUrl("google.com")
                .initialCapital(new BigDecimal(1000))
                .duration(3L)
                .depositFrequency(DepositFrequency.MONTH)
                .additionalContribution(new BigDecimal(200))
                .returnRate(new BigDecimal(2))
                .build()
        );

        investmentService.save(Investment.builder().id(2)
                .companyName("apple")
                .logoUrl("apple.com")
                .initialCapital(new BigDecimal(100000))
                .duration(9L)
                .depositFrequency(DepositFrequency.YEAR)
                .additionalContribution(new BigDecimal(500))
                .returnRate(new BigDecimal(3))
                .build()
        );

        investmentService.save(Investment.builder().id(3)
                .companyName("microsoft")
                .logoUrl("microsoft.com")
                .initialCapital(new BigDecimal(1000000))
                .duration(100L)
                .depositFrequency(DepositFrequency.HALF_YEAR)
                .additionalContribution(new BigDecimal(20000))
                .returnRate(new BigDecimal(1))
                .build()
        );
    }
}
