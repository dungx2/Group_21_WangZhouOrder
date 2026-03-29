package fit.se2.group21.wangzhou.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Create/Update order request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private String description;

    private BigDecimal initialPrice;

    private BigDecimal depositAmount;

    private String specifications;
}

