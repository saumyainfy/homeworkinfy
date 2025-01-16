package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerRewardsDTO {
    private Long customerId;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalRewards;
}
