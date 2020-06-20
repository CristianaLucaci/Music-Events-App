package sample.exceptions;

import sample.model.Event;

public class EventAlreadyExistsException extends Exception{
    private String code;

    public EventAlreadyExistsException(String code) {
        super(String.format("'%s' Event code is unavailable", code));
        this.code=code;
    }

    public String getCode() {
        return code;
    }
}
