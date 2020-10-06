package pl.fintech.challenge1.backend.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvestmentDto {

    private BigDecimal firstDeposit;
    private BigDecimal standingOrder;
    private Integer intervalMonths;
    private Integer durationYears;
    private Integer userId;
}
