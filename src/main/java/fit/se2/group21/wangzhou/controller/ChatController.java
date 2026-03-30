package fit.se2.group21.wangzhou.controller;

import fit.se2.group21.wangzhou.dto.request.MessageRequest;
import fit.se2.group21.wangzhou.dto.response.MessageResponse;
import fit.se2.group21.wangzhou.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


    // --- Standard HTTP Endpoints for rendering views --- //

    @GetMapping("/chat")
    public String chatList(Model model) {
        // Render chat list view
        return "customer/chat";
    }

    @GetMapping("/chat/{conversationId}")
    public String conversation(@PathVariable Integer conversationId, Model model) {
        // Load initial chat history to display when the page loads
        model.addAttribute("history", chatService.getChatHistory(conversationId));
        return "customer/chat";
    }

    // --- WebSocket STOMP Endpoints for real-time messaging --- //

    /**
     * Receives message from: /app/chat/{conversationId}/send
     * Broadcasts to: /topic/conversation/{conversationId}
     */
    @MessageMapping("/chat/{conversationId}/send")
    @SendTo("/topic/conversation/{conversationId}")
    public MessageResponse sendMessage(@DestinationVariable Integer conversationId,
                                       @Payload MessageRequest request,
                                       Principal principal) {

        // Ensure the conversationId matches the payload
        request.setConversationId(conversationId);

        // principal.getName() securely grabs the authenticated user's email
        // Return the saved message, which Spring automatically broadcasts to the @SendTo topic
        return chatService.saveMessage(request, principal.getName());
    }
}