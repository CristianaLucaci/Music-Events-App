package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    Button loginButton;
    public void loginButtonClicked(){
        System.out.println("loginButton clicked");

    }

    public void registerButtonClicked(ActionEvent event) throws IOException {
        Parent registerParent = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }

}
