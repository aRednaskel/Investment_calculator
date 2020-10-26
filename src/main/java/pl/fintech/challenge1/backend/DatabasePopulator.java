package pl.fintech.challenge1.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fintech.challenge1.backend.domain.investment.DepositFrequency;
import pl.fintech.challenge1.backend.domain.investment.Investment;
import pl.fintech.challenge1.backend.domain.investment.InvestmentService;

import java.math.BigDecimal;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements CommandLineRunner {

    private final InvestmentService investmentService;

    private final Random random = new Random();

    private int getRandomNumber(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    //https://www.baeldung.com/java-random-string
    private String getRandomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    @Override
    public void run(String... args) throws Exception {
        for(int n=1; n<=200;n++){
            investmentService.save(Investment.builder().id(n)
                    .companyName("CARE BEAR TFI")
                    .logoUrl("/img/miniaturka.png")
                    .initialCapital(new BigDecimal(getRandomNumber(1000, 1000000)))
                    .duration((long) getRandomNumber(3, 120))
                    .depositFrequency(DepositFrequency.getRandom())
                    .additionalContribution(new BigDecimal(getRandomNumber(0, 10000)))
                    .returnRate(new BigDecimal(getRandomNumber(0, 10)))
                    .build()
            );
        }
    }
}
