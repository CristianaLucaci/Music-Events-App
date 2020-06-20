package sample.model;

import java.util.*;

public class Invitation {
    private String eventDescription;
    private String eventLocation;
    private Date eventDate;
    private String companyName;
    private double paymentOffer;

    public Invitation() {

    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getPaymentOffer() {
        return paymentOffer;
    }

    public void setPaymentOffer(double paymentOffer) {
        this.paymentOffer = paymentOffer;
    }
}
