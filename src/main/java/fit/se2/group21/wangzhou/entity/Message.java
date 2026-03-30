package fit.se2.group21.wangzhou.entity;

import fit.se2.group21.wangzhou.controller.ChatController;
import fit.se2.group21.wangzhou.enums.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Chat message in a conversation
 */
@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", nullable = false)
    private MessageType messageType = MessageType.TEXT;

    @Column(name = "attachment_url")
    private String attachmentUrl;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;
}

