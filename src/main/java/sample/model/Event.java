package sample.model;

import java.util.ArrayList;
import java.util.Date;
<<<<<<< Updated upstream
=======
import java.util.List;
import java.util.Objects;
>>>>>>> Stashed changes

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
        this.bands = new ArrayList<>();
    }

<<<<<<< Updated upstream
=======
    public Event(String eventManagerName, String name, String code, String date, Double ticketPrice, String location, Integer limitOfParticipants, String eventType, String description){
        this.eventManagerName = eventManagerName;
        this.name = name;
        this.code = code;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.location = location;
        this.limitOfParticipants = limitOfParticipants;
        this.eventType = eventType;
        this.description = description;
        this.bands = new ArrayList<>();
    }

>>>>>>> Stashed changes
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
    public void addBand(String band) {
        bands.add(band);
    }
<<<<<<< Updated upstream
=======

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!code.equals(event.code)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventManagerName='" + eventManagerName + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", location='" + location + '\'' +
                ", limitOfParticipants=" + limitOfParticipants +
                ", eventType='" + eventType + '\'' +
                ", description='" + description + '\'' +
                ", bands=" + bands +
                '}';
    }

>>>>>>> Stashed changes
}
