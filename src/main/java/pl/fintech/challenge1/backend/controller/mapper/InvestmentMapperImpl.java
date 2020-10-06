package pl.fintech.challenge1.backend.controller.mapper;

import org.springframework.stereotype.Component;
import pl.fintech.challenge1.backend.controller.dto.InvestmentDto;
import pl.fintech.challenge1.backend.domain.investment.Investment;

@Component
class InvestmentMapperImpl implements InvestmentMapper{
    @Override
    public Investment mapDTOToInvestment(InvestmentDto investmentDto) {
        return Investment.builder()
                .firstDeposit(investmentDto.getFirstDeposit())
                .standingOrder(investmentDto.getStandingOrder())
                .intervalMonths(investmentDto.getIntervalMonths())
                .durationYears(investmentDto.getDurationYears()).build();
    }
}
