package sample.model;

import java.util.Objects;

public class Invite {
    private Event event;
    private String bandName;
    private String details;

    public Invite() {
        this.event = null;
        this.bandName = "";
        this.details = "";
    }

    public Invite(Event event, String bandName, String details) {
        this.event = event;
        this.bandName = bandName;
        this.details = details;
    }

    public Event getEvent() { return event; }
    public String getBandName() { return bandName; }
    public String getDetails() { return details; }
    public void setEvent(Event event) { this.event = event; }
    public void setBandName(String bandName) { this.bandName = bandName; }
    public void setDetails(String details) { this.details = details; }

    @Override
    public String toString() {
        return "Invite{" +
                "event=" + event +
                ", bandName='" + bandName + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
