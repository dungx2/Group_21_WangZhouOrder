package fit.se2.group21.wangzhou.repository;

import fit.se2.group21.wangzhou.entity.ConversationRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for ConversationRead entity
 */
@Repository
public interface ConversationReadRepository extends JpaRepository<ConversationRead, Integer> {

    Optional<ConversationRead> findByConversationIdAndUserId(Integer conversationId, Integer userId);
}

