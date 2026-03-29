package fit.se2.group21.wangzhou.entity;

import fit.se2.group21.wangzhou.enums.CommissionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Commission policy template (Strategy Pattern)
 */
@Entity
@Table(name = "commission_policies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommissionPolicy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "commission_type", nullable = false)
    private CommissionType commissionType;

    @Column(name = "commission_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal commissionValue;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // ...existing relationships...
    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    private List<Commission> commissions;
}

