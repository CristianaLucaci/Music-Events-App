package sample.exceptions;

public class EventDoesNotExistException extends Exception {
    private String code;

    public EventDoesNotExistException(String code) {
        super(String.format("'%s' is not a valid code", code));
        this.code = code;
    }

    public String getUsername() {
        return code;
    }
}
