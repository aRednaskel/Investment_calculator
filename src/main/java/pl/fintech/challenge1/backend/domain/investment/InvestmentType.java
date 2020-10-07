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
public class InvestmentType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;
    private String riskOfDescription;

    private Long firstDeposit;
    private Long systematicPayment;
    private DepositFrequency depositFrequency;
    private Long durationInYears;
    private BigDecimal returnRate;

    @ManyToOne
    private User user;

    private boolean isPublic;
}
