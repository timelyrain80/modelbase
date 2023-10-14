package base.model.common.exception;

public class BadRequestException extends RuntimeException{
    String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }
}
