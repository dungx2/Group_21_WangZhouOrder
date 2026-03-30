package fit.se2.group21.wangzhou.repository;

import fit.se2.group21.wangzhou.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Conversation entity
 */
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    Optional<Conversation> findByOrderId(Integer orderId);
    @Query("SELECT c FROM Conversation c JOIN c.messages m WHERE m.sender.id = :userId")
    List<Conversation> findByParticipantId(@Param("userId") Integer userId);
}

