package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.model.User;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class BandController {

    @FXML
    private Text text;

    @FXML
    private Text mainText;

    @FXML
    private AnchorPane eventsAnchorPane;

    @FXML
    private AnchorPane offersAnchorPane;

    @FXML
    private Text eventDetailsText;

    @FXML
    private Pane eventOfferPane;

    private User currentUser;
    private ArrayList<String> events = new ArrayList<String>();
    private ArrayList<String> offers = new ArrayList<String>();

    @FXML
    public void initialize() {
        currentUser = LoginController.getCurrentUser();
        text.setText("User: " + currentUser.getText2());
        events.add("Out Of Doors 2020");
        events.add("Awake");
        events.add("Vest Fest");
        events.add("Jazz Garana");
        offers.add("Untold");
        offers.add("Custom");
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
        height = 20.0;
        for(String o:offers){
            Button c = new Button(o);
            offersAnchorPane.setTopAnchor(c, height);
            offersAnchorPane.setLeftAnchor(c, 0.0);
            offersAnchorPane.setRightAnchor(c, 300.0);
            c.setOnAction(event -> seeEventOffer(o));
            height = height + 30.0;
            offersAnchorPane.getChildren().add(c);
        }
    }

    public void button1Clicked(ActionEvent event) throws IOException {
        mainText.setText("Your Events");
        eventsAnchorPane.setVisible(true);
        offersAnchorPane.setVisible(false);
        eventOfferPane.setVisible(false);
    }

    public void button2Clicked(ActionEvent event) throws IOException {
        mainText.setText("New Offers");
        eventsAnchorPane.setVisible(false);
        offersAnchorPane.setVisible(true);
        eventDetailsText.setVisible(false);
    }

    public void seeEventOffer(String e){
        System.out.println(e);
        eventOfferPane.setVisible(true);
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
