package fit.se2.group21.wangzhou.enums;

/**
 * Message types in chat conversations
 */
public enum MessageType {
    TEXT("Text"),
    IMAGE("Image"),
    VIDEO("Video"),
    SPEC_CARD("Specification Card"),
    PRICE_PROPOSAL("Price Proposal"),
    VISUAL_PROOF("Visual Proof");

    private final String displayName;

    MessageType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

