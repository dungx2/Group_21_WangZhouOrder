package fit.se2.group21.wangzhou.dto.response;

import fit.se2.group21.wangzhou.enums.CommissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Commission response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommissionResponse {

    private Integer id;

    private BigDecimal amount;

    private String status;

    private String description;

    private String policyName;

    private CommissionType commissionType;
}

