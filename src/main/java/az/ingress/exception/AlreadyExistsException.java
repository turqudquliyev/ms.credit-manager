package az.ingress.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String code) {
        super(code);
    }
}