package pl.fintech.challenge1.backend.domain.investment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestitionParams {
    private Long firstDeposit;
    private Long systematicPayments;
    private Long durationInYears;
    private Long returnOnInvestment;
    private DepositFrequency depositFrequency;
}
