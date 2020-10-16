package pl.fintech.challenge1.backend.domain.investment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
interface InvestmentRepository extends CrudRepository<Investment, Integer> {

    List<Investment> findByInitialCapitalGreaterThanEqualAndReturnRateLessThanEqual(Long initialCapital, BigDecimal returnRate);
}
