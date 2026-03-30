package fit.se2.group21.wangzhou.dto.response;

import fit.se2.group21.wangzhou.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor          // ← required alongside @AllArgsConstructor
@AllArgsConstructor         // ← required for @Builder to work with @Data correctly
public class MessageResponse {
    private Integer messageId;
    private Integer conversationId;
    private Integer senderId;
    private String senderName;
    private String content;
    private MessageType messageType;
    private String attachmentUrl;
    private LocalDateTime createdAt;  // ← add this field
}