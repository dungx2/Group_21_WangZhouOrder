package fit.se2.group21.wangzhou.dto.response;

import fit.se2.group21.wangzhou.enums.MessageType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    private Integer messageId;
    private Integer conversationId;
    private Integer senderId;
    private String senderName;
    private String content;
    private MessageType messageType;
    private String attachmentUrl;
}