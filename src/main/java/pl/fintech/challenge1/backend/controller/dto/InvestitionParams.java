package pl.fintech.challenge1.backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.fintech.challenge1.backend.domain.investment.DepositFrequency;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestitionParams {
    private Long initialCapital;
    private Long duration;
    private Long durationInYears;
    private BigDecimal returnRate;
    private DepositFrequency depositFrequency;
}
