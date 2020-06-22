package sample.exceptions;

import sample.model.Event;

public class BandDoesNotExistException extends Exception{
    private String bandName;

    public BandDoesNotExistException(String bandName) {
        super(String.format("'%s' band does not exists", bandName));
        this.bandName = bandName;
    }

    public String getBandName() {
        return bandName;
    }
}
