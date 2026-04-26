package app.exception;

public class IntegerBoundsViolationException extends Exception {
    public IntegerBoundsViolationException() {}

    public IntegerBoundsViolationException(String message) {
        super(message);
    }
}
