package pl.fintech.challenge1.backend.domain.investment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphData {
    private List<Integer> months;
    private List<BigDecimal> profit;

    public GraphData(int duration) {
        this.months = new ArrayList<>(duration);
        this.profit = new ArrayList<>(duration);
    }

    public void addNextMonthData(Integer month, BigDecimal profit) {
        this.months.add(month);
        this.profit.add(profit);
    }
}
