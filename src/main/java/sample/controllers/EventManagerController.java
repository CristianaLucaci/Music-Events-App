package sample.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.User;

import java.io.IOException;
import java.util.ArrayList;

public class EventManagerController {

    @FXML
    private Text user;

    @FXML
    private Text mainText;

    @FXML
    private AnchorPane eventsAnchorPane;

    @FXML
    private AnchorPane newEventAnchorPane;

    @FXML
    private Text eventDetailsText;

    @FXML
    private Pane eventOfferPane;

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
        eventOfferPane.setVisible(false);
    }

    public void addEventButtonClicked(ActionEvent event) throws IOException {
        mainText.setText("New Offers");
        eventsAnchorPane.setVisible(false);
        newEventAnchorPane.setVisible(true);
        eventDetailsText.setVisible(false);
    }

    public void seeEventDetails(String e){
        System.out.println(e);
        eventDetailsText.setVisible(true);
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