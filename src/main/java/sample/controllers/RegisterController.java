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
    public CheckBox userBox;
    @FXML
    public CheckBox eventManagerBox;
    @FXML
    public CheckBox bandBox;
    @FXML
    public AnchorPane userAnchorPane;
    @FXML
    public AnchorPane genresAnchorPane;
    @FXML
    public Button registerButton;
    @FXML
    public Text genresText;
    @FXML
    public TextField textField1;
    @FXML
    public TextField textField2;
    @FXML
    public TextField emailField;
    @FXML
    public TextField phoneNumberField;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;
    @FXML
    public CheckBox rockCheckBox;
    @FXML
    public CheckBox jazzCheckBox;
    @FXML
    public CheckBox heavyMetalCheckBox;
    @FXML
    public CheckBox reggaeCheckBox;
    @FXML
    public CheckBox latinCheckBox;
    @FXML
    public CheckBox folkCheckBox;
    @FXML
    public CheckBox electronicCheckBox;
    @FXML
    public Text registrationMessage;
    @FXML
    public Button backButton;

    public static String userType;
    public static List<String> genres;

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
        genres = new ArrayList<String>();
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

    @FXML
    public void registerButtonClicked2() throws IOException {

        try {
            //handleGenreCheckBoxes();
            UserService.addUser(userType, textField1.getText(), textField2.getText(), emailField.getText(), phoneNumberField.getText(), usernameField.getText(), passwordField.getText(), genres);
        } catch(UsernameAlreadyExistsException e){
            registrationMessage.setText(e.getMessage());
        }

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

