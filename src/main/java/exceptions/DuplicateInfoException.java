package exceptions;

public class DuplicateInfoException extends RuntimeException {
    public DuplicateInfoException(String errorMessage) { super(errorMessage); }
}
