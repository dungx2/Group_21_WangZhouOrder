package fit.se2.group21.wangzhou.service;

import fit.se2.group21.wangzhou.dto.response.CommissionResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * Commission service interface (Strategy Pattern for calculation)
 */
public interface CommissionService {

    CommissionResponse calculateCommission(Integer affiliateId, BigDecimal orderAmount);

    CommissionResponse getCommissionById(Integer commissionId);

    List<CommissionResponse> getCommissionsByAffiliate(Integer affiliateId);

    BigDecimal getTotalEarnings(Integer affiliateId);

    void approveCommission(Integer commissionId);

    void payCommission(Integer commissionId);
}

