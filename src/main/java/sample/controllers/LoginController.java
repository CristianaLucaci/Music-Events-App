package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import sample.services.UserService;

import java.io.IOException;

public class LoginController {


    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;

    public void loginButtonClicked(ActionEvent event) throws IOException{
        if(UserService.searchUser(usernameField.getText(), passwordField.getText())){
            System.out.println("Ai intrat");
        }
        else{
            System.out.println("Mai incearca");
        }

    }

    public void registerButtonClicked(ActionEvent event) throws IOException {
        Parent registerParent = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }

}
