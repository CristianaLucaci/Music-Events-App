package sample.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.exceptions.EventAlreadyExistsException;
import sample.model.User;
import sample.services.EventService;

import java.io.IOException;
import java.util.ArrayList;

public class EventManagerController {

    @FXML
    private TextField eventName;
    @FXML
    private TextField eventDate;
    @FXML
    private TextField eventCode;
    @FXML
    private TextField eventLocation;
    @FXML
    private TextField ticketPrice;
    @FXML
    private TextField limitOfParticipants;
    @FXML
    private TextField eventType;
    @FXML
    private TextField description;

    @FXML
    private Text user;
    @FXML
    private Text mainText;
    @FXML
    private AnchorPane eventsAnchorPane;
    @FXML
    private AnchorPane newEventAnchorPane;

    private User currentUser;
    private ArrayList<String> events = new ArrayList<String>();

    @FXML
    public void initialize() {
        currentUser = LoginController.getCurrentUser();
        user.setText("User: " + currentUser.getText2());
        events.add("Out Of Doors 2020");
        events.add("Awake");
        events.add("Vest Fest");
        events.add("Jazz Garana");
        double height = 20.0;
        for(String e:events){
            Button b = new Button(e);
            b.setOnAction(event -> seeEventDetails(e));
            eventsAnchorPane.setTopAnchor(b, height);
            eventsAnchorPane.setLeftAnchor(b, 0.0);
            eventsAnchorPane.setRightAnchor(b, 300.0);
            height = height + 30.0;
            eventsAnchorPane.getChildren().add(b);
        }
    }

    public void yourEventsButtonClicked(ActionEvent event) throws IOException {
        mainText.setText("Your Events");
        eventsAnchorPane.setVisible(true);
        newEventAnchorPane.setVisible(false);
    }

    public void addEventButtonClicked(ActionEvent event) throws IOException {
        mainText.setText("New Event");
        eventsAnchorPane.setVisible(false);
        newEventAnchorPane.setVisible(true);
    }

    public void seeEventDetails(String e){
        System.out.println(e);
    }

    public void clearFields() {
        eventName.clear();
        eventDate.clear();
        eventCode.clear();
        eventLocation.clear();
        ticketPrice.clear();
        limitOfParticipants.clear();
        eventType.clear();
        description.clear();
    }

    @FXML
    public void newEventClicked(ActionEvent event) throws IOException {
        try {
            String eventNameStr = eventName.getText();
            String eventCodeStr = eventCode.getText();
            String eventDateStr = eventDate.getText();
            Double price = Double.parseDouble(ticketPrice.getText());
            String locationStr = eventLocation.getText();
            Integer limit = Integer.parseInt(limitOfParticipants.getText());
            String eventTypeStr = eventType.getText();
            String descriptionStr = description.getText();
            //System.out.println(eventNameStr + eventCodeStr + eventDateStr + price + locationStr + limit + eventTypeStr + descriptionStr);

            EventService.addEvent(eventNameStr, eventCodeStr, eventDateStr, price, locationStr, limit, eventTypeStr, descriptionStr);
            clearFields();

            mainText.setText("Your Events");
            eventsAnchorPane.setVisible(true);
            newEventAnchorPane.setVisible(false);
        } catch (EventAlreadyExistsException e) {
            System.out.println("Event already exists");
        }
    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException{
        Parent registerParent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }
}