package fit.se2.group21.wangzhou.dto.request;

import fit.se2.group21.wangzhou.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create message request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRequest {

    private Integer conversationId;

    private String content;

    private MessageType messageType = MessageType.TEXT;

    private String attachmentUrl;
}

