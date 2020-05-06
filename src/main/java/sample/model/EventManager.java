package sample.model;

import java.util.ArrayList;

public class EventManager {
    private String companyName;
    private String address;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private Double sold;
    private ArrayList<Event> events;

    //Empty constructor
    public EventManager() {
        this.companyName = "";
        this.address = "";
        this.email = "";
        this.phoneNumber = "";
        this.username = "";
        this.password = "";
        this.events = null;
        this.sold = 0.0;
    }

    //Getters and setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Double getSold() { return sold; }
    public void setSold(Double sold) { this.sold = sold; }
    public ArrayList<Event> getEvents() { return events; }
    public void setEvents(ArrayList<Event> events) { this.events = events; }

    public void sendInvite(String band) {
        //send invite to band
    }

    public void addEvent() {
        //create new event
    }

    public void editEvent(Event event) {
        //edit event
    }

    public void deleteEvent(Event event) {
        //delete event
    }

    public void updateSold() {
        //update the event manager sold
    }
}
