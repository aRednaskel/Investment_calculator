package pl.fintech.challenge1.backend.domain.investment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.fintech.challenge1.backend.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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

    @Size(min = 1,max = 24)
    private String companyName;
    private String logoUrl;
    private Type type;

    @Min(value = 1000, message = "Value should not be less than 1 000")
    @Max(value = 1000000, message = "Value should not be bigger than 1 000 0000")
    private Long initialCapital;

    @Min(value = 3, message = "Value should not be less than 3")
    @Max(value = 120, message = "Value should not be bigger than 1 000 0000")
    private Long duration;

    @Min(value = 0, message = "Value should not be less than 0")
    @Max(value = 10000, message = "Value should not be bigger than 1 000 0000")
    private Long additionalContribution;

    private DepositFrequency depositFrequency;

    @Min(value = 0, message = "Value should not be less than 0")
    @Max(value = 10, message = "Value should not be bigger than 10")
    private BigDecimal returnRate;

    @ManyToOne
    @JsonIgnore
    private User user;
}
