package pl.fintech.challenge1.backend.domain.investment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface InvestmentRepository extends CrudRepository<InvestmentType, Integer> {
}
