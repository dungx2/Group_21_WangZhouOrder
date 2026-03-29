package fit.se2.group21.wangzhou.enums;

/**
 * Payment status tracking for orders
 */
public enum PaymentStatus {
    UNPAID("Unpaid"),
    DEPOSITED("Deposited"),
    FULLY_PAID("Fully Paid"),
    REFUNDED("Refunded");

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

