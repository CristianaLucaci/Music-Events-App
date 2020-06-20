package sample.controllers;

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

public class ParticipantController {

    @FXML
    private Text text;
    @FXML
    private Text text1;

    private User currentUser;
    @FXML
    public void initialize(){
        currentUser = LoginController.getCurrentUser();
        text.setText("User: " + currentUser.getText2());
    }

}
