package fit.se2.group21.wangzhou.repository;

import fit.se2.group21.wangzhou.entity.CommissionPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for CommissionPolicy entity
 */
@Repository
public interface CommissionPolicyRepository extends JpaRepository<CommissionPolicy, Integer> {

    Optional<CommissionPolicy> findByName(String name);
}

