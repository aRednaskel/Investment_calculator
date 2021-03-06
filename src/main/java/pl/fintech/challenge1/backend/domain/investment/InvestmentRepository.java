package pl.fintech.challenge1.backend.domain.investment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
interface InvestmentRepository extends CrudRepository<Investment, Integer> {
    List<Investment> findByInitialCapitalLessThanEqual(BigDecimal initialCapital);
    Optional<Investment> findByCompanyNameAndInvestmentType(String companyName, InvestmentType investmentType);
}
