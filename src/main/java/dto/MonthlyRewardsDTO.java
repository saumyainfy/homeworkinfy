package dto;

import com.example.homework.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MonthlyRewardsDTO {
    private Long customerId;

    private String customerName;

    @JsonIgnore
    private LocalDate startDate;

    @JsonIgnore
    private LocalDate endDate;

    private Map<String, Integer> monthlyRewards;

    private List<Transaction> transactions;
}
