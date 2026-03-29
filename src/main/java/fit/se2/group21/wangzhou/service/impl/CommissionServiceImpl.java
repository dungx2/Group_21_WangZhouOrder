package fit.se2.group21.wangzhou.service.impl;

import fit.se2.group21.wangzhou.dto.response.CommissionResponse;
import fit.se2.group21.wangzhou.repository.CommissionPolicyRepository;
import fit.se2.group21.wangzhou.repository.CommissionRepository;
import fit.se2.group21.wangzhou.service.CommissionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Commission service implementation (Strategy Pattern for different calculation methods)
 */
@Service
@RequiredArgsConstructor
public class CommissionServiceImpl implements CommissionService {

    private final CommissionRepository commissionRepository;
    private final CommissionPolicyRepository commissionPolicyRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommissionResponse calculateCommission(Integer affiliateId, BigDecimal orderAmount) {
        // TODO: Implement commission calculation using Strategy pattern
        // Support PERCENTAGE, FLAT_FEE, TIERED strategies
        return new CommissionResponse();
    }

    @Override
    public CommissionResponse getCommissionById(Integer commissionId) {
        // TODO: Implement get commission by ID
        return new CommissionResponse();
    }

    @Override
    public List<CommissionResponse> getCommissionsByAffiliate(Integer affiliateId) {
        // TODO: Implement get commissions by affiliate
        return List.of();
    }

    @Override
    public BigDecimal getTotalEarnings(Integer affiliateId) {
        // TODO: Implement calculate total earnings for affiliate
        return BigDecimal.ZERO;
    }

    @Override
    public void approveCommission(Integer commissionId) {
        // TODO: Implement approve commission
    }

    @Override
    public void payCommission(Integer commissionId) {
        // TODO: Implement pay commission (trigger payment)
    }
}

