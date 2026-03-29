package fit.se2.group21.wangzhou.repository;

import fit.se2.group21.wangzhou.entity.User;
import fit.se2.group21.wangzhou.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for User entity (CRUD + custom queries)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);

    Optional<User> findByReferralCode(String referralCode);
}

