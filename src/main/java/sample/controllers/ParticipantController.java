package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.model.User;

import java.io.IOException;
import java.util.ArrayList;

public class ParticipantController {

    @FXML
    private Text text;
    @FXML
    private Text text1;

    private User currentUser;

    @FXML
    private AnchorPane eventsAnchorPane;

    @FXML
    private AnchorPane soldAnchorPane;

    @FXML
    private AnchorPane upcomingEventsAnchorPane;

    @FXML
    private Text descriptionText;

    @FXML
    private Text soldText;

    @FXML
    private Text res;

    @FXML
    private Pane detailsPane;

    private static int var = 10;
    private ArrayList<String> events = new ArrayList<String>();
    private ArrayList<String> upcomings = new ArrayList<String>();

    public String getVar(){
        return " " + var;
    }

    @FXML
    public void initialize(){
        currentUser = LoginController.getCurrentUser();
        text.setText("User: " + currentUser.getText2());
        events.add("Out Of Doors 2020");
        events.add("Awake");
        events.add("Vest Fest");
        events.add("Jazz Garana");
        upcomings.add("Untold");
        upcomings.add("Custom");
        double height = 20.0;
        for(String e:events){
            Button b = new Button(e);
            b.setOnAction(event -> seeEvent(e));
            eventsAnchorPane.setTopAnchor(b, height);
            eventsAnchorPane.setLeftAnchor(b, 0.0);
            eventsAnchorPane.setRightAnchor(b, 300.0);
            height = height + 30.0;
            eventsAnchorPane.getChildren().add(b);
        }
        height = 20.0;
        for(String o:upcomings){
            Button c = new Button(o);
            upcomingEventsAnchorPane.setTopAnchor(c, height);
            upcomingEventsAnchorPane.setLeftAnchor(c, 0.0);
            upcomingEventsAnchorPane.setRightAnchor(c, 300.0);
            c.setOnAction(event -> seeUpcomingEvent(o));
            height = height + 30.0;
            upcomingEventsAnchorPane.getChildren().add(c);
        }
    }

    ListView<String> listView;

    public void button1Clicked(ActionEvent event) throws IOException {
        text1.setText("Your Events");
        eventsAnchorPane.setVisible(true);
        soldAnchorPane.setVisible(false);
        upcomingEventsAnchorPane.setVisible(false);
        detailsPane.setVisible(false);
        descriptionText.setVisible(false);
    }

    public void button2Clicked(ActionEvent event) throws IOException {
        text1.setText("Upcoming Events");
        eventsAnchorPane.setVisible(false);
        soldAnchorPane.setVisible(false);
        upcomingEventsAnchorPane.setVisible(true);
        descriptionText.setVisible(false);
    }

    public void button3Clicked(ActionEvent event) throws IOException {
        text1.setText("Sold");
        res.setText(getVar());
        descriptionText.setVisible(true);
        soldAnchorPane.setVisible(true);
        eventsAnchorPane.setVisible(false);
        upcomingEventsAnchorPane.setVisible(false);
        descriptionText.setVisible(false);
    }

    public void seeUpcomingEvent(String e){
        System.out.println(e);
        detailsPane.setVisible(true);
    }

    public void seeEvent(String e){
        System.out.println(e);
        descriptionText.setVisible(true);
    }

    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException{
        Parent registerParent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }
}
