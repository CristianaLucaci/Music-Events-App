package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class AlertBoxController {

    public boolean yesButtonClicked(javafx.event.ActionEvent event) throws IOException {


        Parent alertParent = FXMLLoader.load(getClass().getResource("/fxml/alertbox.fxml"));
        Scene alertScene = new Scene(alertParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(alertScene);
        window.close();

        return true;
    }

    public boolean noButtonClicked(javafx.event.ActionEvent event) throws IOException {


        Parent alertParent = FXMLLoader.load(getClass().getResource("/fxml/alertbox.fxml"));
        Scene alertScene = new Scene(alertParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(alertScene);
        window.close();

        return false;
    }
}
