package sample.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.exceptions.BandDoesNotExistException;
import sample.exceptions.EventAlreadyExistsException;
import sample.model.Event;
import sample.model.User;
import sample.services.EventService;
import sample.services.InviteService;

import java.io.IOException;
import java.util.List;

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
    private TextArea eventDetailsArea;
    @FXML
    private Button inviteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    @FXML
    private Text user;
    @FXML
    private Text mainText;
    @FXML
    private AnchorPane eventsAnchorPane;
    @FXML
    private AnchorPane newEventAnchorPane;

    private User currentUser;
    private List<Event> events;
    private Event currentEvent = null;
    private Button currentButtonPressed;
    private Stage newWindow;

    @FXML
    public void initialize() {
        currentUser = LoginController.getCurrentUser();
        user.setText("User: " + currentUser.getText2());
        events = EventService.getEvents();

        double height = 20.0;
        for(Event ev : events){
            if (ev.getEventManagerName().equals(currentUser.getUsername())) {
                Button b = new Button(ev.getName());
                b.setOnAction(event -> seeEventDetails(ev.getDescription(), ev, b));
                b.setPrefSize(20, 5);
                eventsAnchorPane.setTopAnchor(b, height);
                eventsAnchorPane.setLeftAnchor(b, 0.0);
                eventsAnchorPane.setRightAnchor(b, 300.0);
                height = height + 30.0;
                eventsAnchorPane.getChildren().add(b);
            }
        }
    }

    public void inviteButtonPressed(ActionEvent event) throws IOException {

        Label label = new Label("Invite Band");
        TextField bandName = new TextField();
        TextField invitationMessage = new TextField();
        Button inviteButton = new Button("Send Invite");

        bandName.setPromptText("Band Name");
        invitationMessage.setPromptText("Message");
        invitationMessage.promptTextProperty();
        invitationMessage.setPrefSize(50, 100);
        inviteButton.setOnAction(e -> sendInviteButtonPressed(inviteButton, bandName.getText(), invitationMessage.getText()));

        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(label, bandName, invitationMessage, inviteButton);

        label.setFont(Font.font(20));
        Scene secondScene = new Scene(vb, 230, 300);

        // New window (Stage)
        newWindow = new Stage();
        newWindow.setTitle("Invite");
        newWindow.setScene(secondScene);

        newWindow.show();
    }

    public void sendInviteButtonPressed(Button b, String bandName, String message) {
        try {
            InviteService.newInvite(currentEvent, bandName, message);
            newWindow.close();

            eventDetailsArea.setVisible(false);
            inviteButton.setVisible(false);
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        } catch (BandDoesNotExistException e) {
            //inviteErrorLabel.setText(e.getMessage());
            System.out.println(e);
        }

    }

    public void deleteButtonPressed(ActionEvent e) {
        events.remove(currentEvent);
        EventService.persistEvents();

        eventDetailsArea.setVisible(false);
        inviteButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);

        eventsAnchorPane.getChildren().remove(currentButtonPressed);
        initialize();
    }

    public void seeEventDetails(String description, Event ev, Button button){
        currentEvent = ev;
        currentButtonPressed = button;
        eventDetailsArea.setText(description);
        eventDetailsArea.setVisible(true);
        inviteButton.setVisible(true);
        editButton.setVisible(true);
        deleteButton.setVisible(true);
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
            String eventManagerStr = currentUser.getUsername();
            //System.out.println(eventNameStr + eventCodeStr + eventDateStr + price + locationStr + limit + eventTypeStr + descriptionStr);

            EventService.addEvent(eventManagerStr, eventNameStr, eventCodeStr, eventDateStr, price, locationStr, limit, eventTypeStr, descriptionStr);
            clearFields();
            initialize();

            eventDetailsArea.setVisible(false);
            inviteButton.setVisible(false);
            editButton.setVisible(false);
            deleteButton.setVisible(false);

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