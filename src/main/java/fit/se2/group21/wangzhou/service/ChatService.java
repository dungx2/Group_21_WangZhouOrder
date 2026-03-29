package fit.se2.group21.wangzhou.service;

import fit.se2.group21.wangzhou.dto.request.MessageRequest;

/**
 * Chat service interface (WebSocket real-time messaging)
 */
public interface ChatService {

    void sendMessage(Integer userId, MessageRequest request);

    void markMessageAsRead(Integer messageId);

    void updateUnreadCount(Integer conversationId, Integer userId);

    void notifyNewMessage(Integer conversationId, String messageContent);
}

