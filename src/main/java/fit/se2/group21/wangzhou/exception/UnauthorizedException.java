package fit.se2.group21.wangzhou.exception;

/**
 * Exception thrown when user is not authorized to perform an action
 */
public class UnauthorizedException extends AppException {

    public UnauthorizedException(String message) {
        super(message);
    }
}

