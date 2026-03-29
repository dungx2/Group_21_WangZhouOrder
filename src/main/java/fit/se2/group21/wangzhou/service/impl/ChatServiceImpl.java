package fit.se2.group21.wangzhou.service.impl;

import fit.se2.group21.wangzhou.dto.request.MessageRequest;
import fit.se2.group21.wangzhou.repository.ConversationReadRepository;
import fit.se2.group21.wangzhou.repository.MessageRepository;
import fit.se2.group21.wangzhou.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Chat service implementation
 */
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final MessageRepository messageRepository;
    private final ConversationReadRepository conversationReadRepository;

    @Override
    public void sendMessage(Integer userId, MessageRequest request) {
        // TODO: Implement send message logic (WebSocket broadcast)
    }

    @Override
    public void markMessageAsRead(Integer messageId) {
        // TODO: Implement mark message as read
    }

    @Override
    public void updateUnreadCount(Integer conversationId, Integer userId) {
        // TODO: Implement update unread count
    }

    @Override
    public void notifyNewMessage(Integer conversationId, String messageContent) {
        // TODO: Implement notify new message to WebSocket subscribers
    }
}

