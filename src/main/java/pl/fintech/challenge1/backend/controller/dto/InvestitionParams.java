package pl.fintech.challenge1.backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import pl.fintech.challenge1.backend.domain.investment.DepositFrequency;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestitionParams {

    @Min(value = 1000, message = "Value should not be less than 1 000")
    @Max(value = 1000000, message = "Value should not be bigger than 1 000 0000")
    private Long initialCapital;

    @Min(value = 3, message = "Value should not be less than 3")
    @Max(value = 120, message = "Value should not be bigger than 1 000 0000")
    private Long duration;

    @Min(value = 0, message = "Value should not be less than 0")
    @Max(value = 10000, message = "Value should not be bigger than 1 000 0000")
    private Long additionalContribution;

    private DepositFrequency depositFrequency;

    @Min(value = 0, message = "Value should not be less than 0")
    @Max(value = 10, message = "Value should not be bigger than 10")
    private BigDecimal returnRate;
}
