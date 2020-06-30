package sample.exceptions;

public class TicketAlreadyBoughtException extends Exception {
    private String eventName;

    public TicketAlreadyBoughtException(String eventName) {
        super(String.format("'%s' band already invited", eventName));
        this.eventName = eventName;
    }

}
