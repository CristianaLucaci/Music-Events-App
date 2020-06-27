package sample.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.model.Event;
import sample.model.User;
import sample.services.EventService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private TextArea textEv;

    @FXML
    private Text soldText;

    @FXML
    private Text res;

    @FXML
    private Pane detailsPane;

    @FXML
    private TextField moneyInput;

    private static int var;
    private List<Event> events;
    private List<Event> upcomings;

    public String printVar(){
        return " " + var;
    }
    private boolean addMoney(TextField input, String message){
        try{
            int money = Integer.parseInt(input.getText());
            var=var+money;
            return true;
        }catch(NumberFormatException e){
            System.out.println("Error: " + message + " is not a number");
            return false;
        }
    }

    private boolean retrieveMoney(TextField input, String message){
        try{
            int money = Integer.parseInt(input.getText());
            var=var-money;
            return true;
        }catch(NumberFormatException e){
            System.out.println("Error: " + message + " is not a number");
            return false;
        }
    }

    @FXML
    public void initialize(){
        currentUser = LoginController.getCurrentUser();
        text.setText("User: " + currentUser.getText2());
        upcomings= EventService.getEvents();

        double height = 20.0;
        for(Event e:upcomings){
            Button b = new Button(e.getName());
            b.setOnAction(event -> seeUpcomingEvent(e));
            upcomingEventsAnchorPane.setTopAnchor(b, height);
            upcomingEventsAnchorPane.setLeftAnchor(b, 0.0);
            upcomingEventsAnchorPane.setRightAnchor(b, 300.0);
            height = height + 30.0;
            upcomingEventsAnchorPane.getChildren().add(b);
            System.out.println(e.getName());
        }
    }


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
        res.setText(printVar());
        descriptionText.setVisible(true);
        soldAnchorPane.setVisible(true);
        eventsAnchorPane.setVisible(false);
        upcomingEventsAnchorPane.setVisible(false);
        descriptionText.setVisible(false);
    }

    public void addMoneyClicked(ActionEvent event) throws IOException{
        text1.setText("Sold");
        addMoney(moneyInput, moneyInput.getText());
        res.setText(printVar());
        descriptionText.setVisible(true);
        soldAnchorPane.setVisible(true);
        eventsAnchorPane.setVisible(false);
        upcomingEventsAnchorPane.setVisible(false);
        descriptionText.setVisible(false);
    }

    public void retrieveMoneyClicked(ActionEvent event) throws IOException{
        text1.setText("Sold");
        retrieveMoney(moneyInput, moneyInput.getText());
        res.setText(printVar());
        descriptionText.setVisible(true);
        soldAnchorPane.setVisible(true);
        eventsAnchorPane.setVisible(false);
        upcomingEventsAnchorPane.setVisible(false);
        descriptionText.setVisible(false);
    }

    public void buyTicketClicked(ActionEvent event)throws IOException{
        Stage window=new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("BuyTicket");

        Parent alertParent = FXMLLoader.load(getClass().getResource("/fxml/alertbox.fxml"));
        Scene alertScene = new Scene(alertParent);
        window.setScene(alertScene);
        window.showAndWait();
    }

    public void seeEvent(String e){
        System.out.println(e);
        detailsPane.setVisible(true);
    }

    public void seeUpcomingEvent(Event e){
        detailsPane.setVisible(true);
       textEv.setText("Nume eveniment: "+e.getName()+"\n" + "Organizator: " + e.getEventManagerName() + "\n" + "Descriere: " + e.getDescription());

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
