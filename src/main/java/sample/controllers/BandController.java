package sample.controllers;

import javafx.fxml.FXML;
import sample.model.User;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import java.io.IOException;

public class BandController {

    @FXML
    private Text text;

    private User currentUser;

    public void printCurrentUser(ActionEvent event) throws IOException {
        currentUser = LoginController.getCurrentUser();
        text.setText(currentUser.getUsername());
        System.out.println(currentUser.getUsername());
    }

}
