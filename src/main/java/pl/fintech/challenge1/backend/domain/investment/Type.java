package pl.fintech.challenge1.backend.domain.investment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Type {
    TIME_DEPOSIT,  SECURITIES, BONDS, EQUITY;

    //https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
    private static final List<Type> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Type getRandom(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
