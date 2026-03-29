package fit.se2.group21.wangzhou.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tracks conversation read status per user
 */
@Entity
@Table(name = "conversation_reads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationRead extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "last_read_message_id")
    private Integer lastReadMessageId;

    @Column(name = "unread_count", nullable = false)
    private Integer unreadCount = 0;
}

