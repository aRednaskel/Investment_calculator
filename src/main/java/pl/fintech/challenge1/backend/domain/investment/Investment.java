package pl.fintech.challenge1.backend.domain.investment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.fintech.challenge1.backend.domain.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Investment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String companyName;
    private String logoUrl;

    private Long initialCapital;
    private Long duration;
    private Long additionalContribution;
    private DepositFrequency depositFrequency;
    private BigDecimal returnRate;

    @ManyToOne
    private User user;
}
