package pl.fintech.challenge1.backend.domain.investment;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Getter
public enum DepositFrequency {
    NULL(0),
    MONTH(1),
    QUARTER(3),
    HALF_YEAR(6),
    YEAR(12);

    private int numberOfMonths;
    //https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
    private static final List<DepositFrequency> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    DepositFrequency(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public static DepositFrequency getRandom(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
