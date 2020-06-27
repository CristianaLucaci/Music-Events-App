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
import sample.model.Event;
import sample.model.Invitation;
import sample.model.Invite;
import sample.model.User;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import sample.services.EventService;
import sample.services.InviteService;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private TextArea eventDetailsText;

    @FXML
    private TextArea offerDetailsText;

    @FXML
    private Pane eventOfferPane;

    private User currentUser;
    private List<Event> events;
    private List<Invite> offers;

    @FXML
    public void initialize() {
        currentUser = LoginController.getCurrentUser();
        text.setText("User: " + currentUser.getText1());
        events = EventService.getEvents();
        offers = InviteService.getInvites();

        double height = 20.0;
        for(Event e:events){
            if(e.toString().contains(currentUser.getText1())) {
                Button b = new Button(e.getName());
                b.setOnAction(event -> seeEventDetails(e));
                eventsAnchorPane.setTopAnchor(b, height);
                eventsAnchorPane.setLeftAnchor(b, 0.0);
                eventsAnchorPane.setRightAnchor(b, 300.0);
                height = height + 30.0;
                eventsAnchorPane.getChildren().add(b);
                System.out.println(e.getName());
            }
        }
        height = 20.0;
        for(Invite o:offers){
            if(o.getBandName().equals(currentUser.getText1())) {
                Button c = new Button(o.getEvent().getName());
                offersAnchorPane.setTopAnchor(c, height);
                offersAnchorPane.setLeftAnchor(c, 0.0);
                offersAnchorPane.setRightAnchor(c, 300.0);
                c.setOnAction(event -> seeEventOffer(o));
                height = height + 30.0;
                offersAnchorPane.getChildren().add(c);
            }
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

    public void seeEventOffer(Invite o){
        eventOfferPane.setVisible(true);
        offerDetailsText.setText(o.getEvent().getName() + "\n" + o.getDetails());
    }

    public void seeEventDetails(Event e){
        eventDetailsText.setText("Nume eveniment: " + e.getName() + "\n" + "Organizator: " + e.getEventManagerName() + "\n" + "Descriere: " + e.getDescription());
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

    @FXML
    public void acceptInvitation(ActionEvent event) throws IOException{
        String[] details = offerDetailsText.getText().split("\n", 2);
        String eventName = details[0];
        for(Event ev:events){
            if(ev.getName().equals(eventName)) {
                ev.addBand(currentUser.getText1());
                for(Iterator<Invite> it = offers.iterator(); it.hasNext();) {
                    Invite invite = it.next();
                    if (invite.getBandName().equals(currentUser.getText1()) && invite.getEvent().getName().equals(eventName)) {
                        it.remove();
                        offersAnchorPane.getChildren().removeIf(n -> n instanceof Button);
                        eventOfferPane.setVisible(false);
                    }
                }
                EventService.persistEvents();
                InviteService.persistInvites();
                initialize();
            }
        }

    }

    @FXML
    public void declineInvitation(ActionEvent event) throws IOException{
        String[] details = offerDetailsText.getText().split("\n", 2);
        String eventName = details[0];
        for(Event ev:events){
            if(ev.getName().equals(eventName)) {
                for(Iterator<Invite> it = offers.iterator(); it.hasNext();) {
                    Invite invite = it.next();
                    if (invite.getBandName().equals(currentUser.getText1()) && invite.getEvent().getName().equals(eventName)) {
                        it.remove();
                        offersAnchorPane.getChildren().removeIf(n -> n instanceof Button);
                        eventOfferPane.setVisible(false);
                    }
                }
                InviteService.persistInvites();
                initialize();
            }
        }

    }

}
