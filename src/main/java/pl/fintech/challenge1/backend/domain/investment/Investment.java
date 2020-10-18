package pl.fintech.challenge1.backend.domain.investment;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Integer id;

    private String companyName;
    private String logoUrl;
    private Type type;

    private Long initialCapital;
    private Long duration;
    private Long additionalContribution;
    private DepositFrequency depositFrequency;
    private BigDecimal returnRate;

    @ManyToOne
    @JsonIgnore
    private User user;
}
