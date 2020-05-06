package sample.model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String name;
    private String code;
    private Date date;
    private Double ticketPrice;
    private String location;
    private Integer limitOfParticipants;
    private ArrayList<String> eventType;
    private String description;
    private ArrayList<String> bands;

    //Empty constructor
    public Event() {
        this.name = "";
        this.code = "";
        this.date = null;
        this.ticketPrice = 0.0;
        this.location = "";
        this.limitOfParticipants = 0;
        this.eventType = null;
        this.description = "";
        this.bands = null;
    }

    //Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public Double getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(Double ticketPrice) { this.ticketPrice = ticketPrice; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Integer getLimitOfParticipants() { return limitOfParticipants; }
    public void setLimitOfParticipants(Integer limitOfParticipants) { this.limitOfParticipants = limitOfParticipants; }
    public ArrayList<String> getEventType() { return eventType; }
    public void setEventType(ArrayList<String> eventType) { this.eventType = eventType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ArrayList<String> getBands() { return bands; }
    public void setBands(ArrayList<String> bands) { this.bands = bands; }

    //Adding a new band to the event
    public void inviteNewBand(String band) {
        bands.add(band);
    }
}
