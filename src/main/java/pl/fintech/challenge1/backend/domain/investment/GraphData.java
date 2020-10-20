package pl.fintech.challenge1.backend.domain.investment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class GraphData {
    private List<Integer> months;
    private List<BigDecimal> profits;

    public GraphData(List<BigDecimal> profits) {
        this.months = new ArrayList<>();
        for (int i = 1; i <= profits.size(); i++) {
            months.add(i);
        }
        this.profits = profits;
    }

    public GraphData(int duration) {
        this.months = new ArrayList<>(duration);
        this.profits = new ArrayList<>(duration);
    }

    public void addNextMonthData(Integer month, BigDecimal profit) {
        this.months.add(month);
        this.profits.add(profit);
    }
}
