package pl.fintech.challenge1.backend.domain.investment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;

    @Override
    public Investment save(Investment investment) {
        return investmentRepository.save(investment);
    }
}
