package fit.se2.group21.wangzhou.dto.response;

import fit.se2.group21.wangzhou.enums.OrderStatus;
import fit.se2.group21.wangzhou.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Integer id;

    private String orderNumber;

    private String description;

    private BigDecimal initialPrice;

    private BigDecimal finalPrice;

    private BigDecimal depositAmount;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

