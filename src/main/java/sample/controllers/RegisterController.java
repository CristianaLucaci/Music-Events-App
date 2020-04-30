package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private CheckBox userBox;
    @FXML
    private CheckBox eventManagerBox;
    @FXML
    private CheckBox bandBox;
    @FXML
    private AnchorPane userAnchorPane;
    @FXML
    private AnchorPane eventManagerAnchorPane;
    @FXML
    private AnchorPane bandAnchorPane;
    @FXML
    private Button registerButton;

    @FXML
    private void handleUserBox(){
        if(userBox.isSelected()){
            eventManagerBox.setSelected(false);
            bandBox.setSelected(false);
            userAnchorPane.setVisible(true);
            eventManagerAnchorPane.setVisible(false);
            bandAnchorPane.setVisible(false);
        }
    }

    @FXML
    private void handleEventManagerBox(){
        if(eventManagerBox.isSelected()){
            userBox.setSelected(false);
            bandBox.setSelected(false);
            userAnchorPane.setVisible(false);
            eventManagerAnchorPane.setVisible(true);
            bandAnchorPane.setVisible(false);
        }
    }

    @FXML
    private void handleBandBox(){
        if(bandBox.isSelected()){
            eventManagerBox.setSelected(false);
            userBox.setSelected(false);
            userAnchorPane.setVisible(false);
            eventManagerAnchorPane.setVisible(false);
            bandAnchorPane.setVisible(true);
        }
    }

    @FXML
    public void registerButtonClicked(ActionEvent event) throws IOException {
        Parent registerParent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }
}

