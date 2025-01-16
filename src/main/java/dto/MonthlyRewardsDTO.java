package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
public class MonthlyRewardsDTO {
    private Long customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Map<String, Integer> monthlyRewards;
}
