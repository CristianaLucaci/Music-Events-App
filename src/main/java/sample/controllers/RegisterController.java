package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.exceptions.UsernameAlreadyExistsException;
import sample.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private AnchorPane genresAnchorPane;
    @FXML
    private Button registerButton;
    @FXML
    private Text genresText;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private CheckBox rockCheckBox;
    @FXML
    private CheckBox jazzCheckBox;
    @FXML
    private CheckBox heavyMetalCheckBox;
    @FXML
    private CheckBox reggaeCheckBox;
    @FXML
    private CheckBox latinCheckBox;
    @FXML
    private CheckBox folkCheckBox;
    @FXML
    private CheckBox electronicCheckBox;
    @FXML
    private Text registrationMessage;

    private static String userType;
    private static List<String> genres;

    @FXML
    private void handleUserBox(){
        if(userBox.isSelected()){
            eventManagerBox.setSelected(false);
            bandBox.setSelected(false);
            userAnchorPane.setVisible(true);
            genresAnchorPane.setVisible(true);
            textField1.setPromptText("First Name");
            textField2.setPromptText("Last Name");
            genresText.setText("Preferred genres: ");
            userType = "Participant";
        }
    }

    @FXML
    private void handleEventManagerBox(){
        if(eventManagerBox.isSelected()){
            userBox.setSelected(false);
            bandBox.setSelected(false);
            userAnchorPane.setVisible(true);
            genresAnchorPane.setVisible(false);
            textField1.setPromptText("Company Name");
            textField2.setPromptText("Address");
            userType = "Event Manager";
        }
    }

    @FXML
    private void handleBandBox(){
        if(bandBox.isSelected()){
            eventManagerBox.setSelected(false);
            userBox.setSelected(false);
            userAnchorPane.setVisible(true);
            genresAnchorPane.setVisible(true);
            textField1.setPromptText("Band Name");
            textField2.setPromptText("Most Popular Album");
            genresText.setText("Played genres: ");
            userType = "Band";
            handleGenreCheckBoxes();
        }
    }

    @FXML
    private void handleGenreCheckBoxes(){
        genres = new ArrayList<>();
        if(rockCheckBox.isSelected())
            genres.add("Rock");
        if(jazzCheckBox.isSelected())
            genres.add("Jazz");
        if(heavyMetalCheckBox.isSelected())
            genres.add("Heavy Metal");
        if(reggaeCheckBox.isSelected())
            genres.add("Reggae");
        if(latinCheckBox.isSelected())
            genres.add("Latin");
        if(folkCheckBox.isSelected())
            genres.add("Folk");
        if(electronicCheckBox.isSelected())
            genres.add("Electronic");
    }

    public void handleRegisterAction() {
        try {
            handleGenreCheckBoxes();
            UserService.addUser(userType, textField1.getText(), textField2.getText(), emailField.getText(), phoneNumberField.getText(), usernameField.getText(), passwordField.getText(), genres);

        } catch (UsernameAlreadyExistsException e) {
            System.out.println("Username-ul exista deja");
        }
    }

    @FXML
    public void registerButtonClicked(ActionEvent event) throws IOException {

            try {
                handleGenreCheckBoxes();
                UserService.addUser(userType, textField1.getText(), textField2.getText(), emailField.getText(), phoneNumberField.getText(), usernameField.getText(), passwordField.getText(), genres);
                Parent registerParent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
                Scene registerScene = new Scene(registerParent);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(registerScene);
                window.show();
            } catch(UsernameAlreadyExistsException e){
                registrationMessage.setText(e.getMessage());
            }

    }
}

