package az.ingress.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String code) {
        super(code);
    }
}