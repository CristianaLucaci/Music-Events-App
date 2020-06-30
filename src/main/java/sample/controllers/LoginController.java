package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import sample.exceptions.UsernameDoesNotExistException;
import sample.model.User;
import sample.services.UserService;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;
    @FXML
    public Button loginButton;
    @FXML
    public Text loginErrorText;

    private static User currentUser = new User();

    public void loginButtonClicked(){
        currentUser = UserService.searchUser(usernameField.getText(),passwordField.getText());
        String userType = UserService.searchUser(usernameField.getText(),passwordField.getText()).getUserType();
    }

    public void registerButtonClicked(ActionEvent event) throws IOException {
        Parent registerParent = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
