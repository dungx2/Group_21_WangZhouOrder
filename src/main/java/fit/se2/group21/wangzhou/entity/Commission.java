package fit.se2.group21.wangzhou.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Commission record for affiliate earnings
 */
@Entity
@Table(name = "commissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "affiliate_id", nullable = false)
    private User user;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "status")
    private String status = "PENDING"; // PENDING, APPROVED, PAID

    @Column(name = "description")
    private String description;

    // ...existing relationships...
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private CommissionPolicy policy;
}

