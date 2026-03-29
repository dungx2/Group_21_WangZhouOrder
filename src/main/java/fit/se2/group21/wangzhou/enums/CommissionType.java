package fit.se2.group21.wangzhou.enums;

/**
 * Commission calculation strategies (Strategy Pattern)
 */
public enum CommissionType {
    PERCENTAGE("Percentage"),
    FLAT_FEE("Flat Fee"),
    TIERED("Tiered");

    private final String displayName;

    CommissionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

