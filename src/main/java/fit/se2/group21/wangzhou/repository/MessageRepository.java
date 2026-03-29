package fit.se2.group21.wangzhou.repository;

import fit.se2.group21.wangzhou.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Message entity
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByConversationId(Integer conversationId);

    List<Message> findByConversationIdOrderByCreatedAtDesc(Integer conversationId);
}

