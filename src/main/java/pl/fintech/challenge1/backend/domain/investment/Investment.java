package pl.fintech.challenge1.backend.domain.investment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.fintech.challenge1.backend.domain.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "investments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Investment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private BigDecimal firstDeposit;
    private BigDecimal standingOrder;
    private Integer intervalMonths;
    private Integer durationYears;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
