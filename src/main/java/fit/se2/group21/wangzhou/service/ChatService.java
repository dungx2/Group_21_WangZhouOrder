package fit.se2.group21.wangzhou.service;

import fit.se2.group21.wangzhou.dto.request.MessageRequest;
import fit.se2.group21.wangzhou.dto.response.MessageResponse;

import java.util.List;

/**
 * Chat service interface (WebSocket real-time messaging)
 */
public interface ChatService {

    MessageResponse saveMessage(MessageRequest request, String senderEmail);
    List<MessageResponse> getChatHistory(Integer conversationId);

}

