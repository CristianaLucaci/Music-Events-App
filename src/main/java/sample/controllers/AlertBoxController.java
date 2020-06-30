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

    public static boolean res=false;

    public void yesButtonClicked(javafx.event.ActionEvent event) throws IOException {


        Parent alertParent = FXMLLoader.load(getClass().getResource("/fxml/alertbox.fxml"));
        Scene alertScene = new Scene(alertParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(alertScene);
        window.close();

        res=true;
    }

    public void noButtonClicked(javafx.event.ActionEvent event) throws IOException {


        Parent alertParent = FXMLLoader.load(getClass().getResource("/fxml/alertbox.fxml"));
        Scene alertScene = new Scene(alertParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(alertScene);
        window.close();

        res=false;
    }

    public static boolean getRes() {
        return res;
    }
}
