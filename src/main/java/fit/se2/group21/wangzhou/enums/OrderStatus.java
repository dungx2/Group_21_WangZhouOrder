package fit.se2.group21.wangzhou.enums;

/**
 * Order status lifecycle (State Pattern)
 */
public enum OrderStatus {
    NEGOTIATING("Negotiating"),
    PENDING_DEPOSIT("Pending Deposit"),
    IN_PRODUCTION("In Production"),
    PENDING_APPROVAL("Pending Approval"),
    READY_FOR_SHIPPING("Ready for Shipping"),
    DONE("Done"),
    CANCELLED("Cancelled");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

