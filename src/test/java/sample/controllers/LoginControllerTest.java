package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.services.FileSystemService;
import javafx.scene.control.Button;
import sample.services.UserService;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest extends ApplicationTest {

    private LoginController controller;
    public static final String username = "test123";
    public static final String password = "parola123";

    @BeforeClass
    public static void setupClass() {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws Exception {
        controller = new LoginController();
        UserService.loadUsersFromFile();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.usernameField.setText(username);
        controller.passwordField.setText(password);

    }

    @Test
    void loginButtonClickedTest() {
        controller.loginButtonClicked();
    }

    @Test
    void registerButtonClicked() {
    }

    @Test
    void getCurrentUser() {
    }
}