package pl.fintech.challenge1.backend.domain.investment;

import lombok.Getter;

@Getter
public enum DepositFrequency {
    NULL(0),
    MONTH(1),
    QUARTER(3),
    HALF_YEAR(6),
    YEAR(12);

    private int numberOfMonths;

    DepositFrequency(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }
}
