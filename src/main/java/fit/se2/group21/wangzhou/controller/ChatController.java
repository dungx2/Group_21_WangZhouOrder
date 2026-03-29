package fit.se2.group21.wangzhou.controller;

import fit.se2.group21.wangzhou.dto.request.MessageRequest;
import fit.se2.group21.wangzhou.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Chat controller for real-time messaging
 */
@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public String chatList(Model model) {
        // TODO: Get user's conversations and return chat list
        return "customer/chat";
    }

    @GetMapping("/{conversationId}")
    public String conversation(@PathVariable Integer conversationId, Model model) {
        // TODO: Get conversation messages and return chat.html
        return "customer/chat";
    }

    @PostMapping("/{conversationId}/send")
    public String sendMessage(@PathVariable Integer conversationId, 
                            @ModelAttribute MessageRequest request) {
        // TODO: Send message and broadcast via WebSocket
        return "redirect:/chat/{conversationId}";
    }

    @PostMapping("/{conversationId}/mark-read")
    public String markAsRead(@PathVariable Integer conversationId) {
        // TODO: Mark conversation as read
        return "redirect:/chat/{conversationId}";
    }
}

