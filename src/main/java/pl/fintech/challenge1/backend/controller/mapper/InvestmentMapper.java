package pl.fintech.challenge1.backend.controller.mapper;

import pl.fintech.challenge1.backend.controller.dto.InvestmentDto;
import pl.fintech.challenge1.backend.domain.investment.Investment;

public interface InvestmentMapper {
    Investment mapDTOToInvestment(InvestmentDto investmentDto);
}
