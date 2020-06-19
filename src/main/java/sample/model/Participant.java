package sample.model;

import java.util.ArrayList;
import java.util.List;

public class Participant {
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;
   private String username;
   private String password;
   private List<String> genres;
   private Double sold;
   private ArrayList<Event> events;

   public Participant(){
       this.firstName="";
       this.lastName="";
       this.email="";
       this.phoneNumber="";
       this.username="";
       this.password="";
       this.genres=null;
       this.sold=0.0;
       this.events=null;
   }

   //getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void viewUpcomingEvents(){
       //view event
    }

    public void soldUpdate(){
       //update the Participant Sold
    }
}

