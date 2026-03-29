package fit.se2.group21.wangzhou.repository;

import fit.se2.group21.wangzhou.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Commission entity
 */
@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {

    List<Commission> findByUserId(Integer userId);

    List<Commission> findByUserIdAndStatus(Integer userId, String status);
}

