package sample.exceptions;

import sample.model.Event;

public class BandAlreadyInvitedException extends Exception{
    private String bandName;

    public BandAlreadyInvitedException(String bandName) {
        super(String.format("'%s' band already invited", bandName));
        this.bandName = bandName;
    }

    public String getBandName() {
        return bandName;
    }
}
