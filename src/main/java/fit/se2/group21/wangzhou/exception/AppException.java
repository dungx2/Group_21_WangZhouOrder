package fit.se2.group21.wangzhou.exception;

/**
 * Base custom application exception
 */
public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}

