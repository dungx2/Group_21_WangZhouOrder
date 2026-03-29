package fit.se2.group21.wangzhou.enums;

/**
 * User roles in the system (RBAC)
 */
public enum Role {
    CUSTOMER("Customer"),
    AFFILIATE("Affiliate"),
    ADMIN("Administrator");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

