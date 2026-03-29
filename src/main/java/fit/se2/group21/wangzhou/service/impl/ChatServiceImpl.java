package fit.se2.group21.wangzhou.service.impl;

import fit.se2.group21.wangzhou.dto.request.MessageRequest;
import fit.se2.group21.wangzhou.dto.response.MessageResponse;
import fit.se2.group21.wangzhou.entity.Conversation;
import fit.se2.group21.wangzhou.entity.Message;
import fit.se2.group21.wangzhou.entity.User;
import fit.se2.group21.wangzhou.repository.ConversationRepository;
import fit.se2.group21.wangzhou.repository.MessageRepository;
import fit.se2.group21.wangzhou.repository.UserRepository;
import fit.se2.group21.wangzhou.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public ChatServiceImpl(MessageRepository messageRepository, ConversationRepository conversationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public MessageResponse saveMessage(MessageRequest request, String senderEmail) {
        // 1. Fetch related entities
        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Conversation conversation = conversationRepository.findById(request.getConversationId())
                .orElseThrow(() -> new IllegalArgumentException("Conversation not found"));

        // 2. Build and save the message entity
        Message message = Message.builder()
                .conversation(conversation)
                .sender(sender)
                .content(request.getContent())
                .messageType(request.getMessageType())
                .attachmentUrl(request.getAttachmentUrl())
                .isRead(false)
                .build();

        Message savedMessage = messageRepository.save(message);

        // 3. Map to Response DTO
        return mapToResponse(savedMessage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageResponse> getChatHistory(Integer conversationId) {
        // Requires a repository method: findByConversationIdOrderByCreatedAtAsc
        List<Message> messages = messageRepository.findByConversationIdOrderByCreatedAtAsc(conversationId);

        return messages.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private MessageResponse mapToResponse(Message message) {
        return MessageResponse.builder()
                .messageId(message.getId())
                .conversationId(message.getConversation().getId())
                .senderId(message.getSender().getId())
                .senderName(message.getSender().getName()) // Assuming User entity has a getName() method
                .content(message.getContent())
                .messageType(message.getMessageType())
                .attachmentUrl(message.getAttachmentUrl())
                .build();
    }
}