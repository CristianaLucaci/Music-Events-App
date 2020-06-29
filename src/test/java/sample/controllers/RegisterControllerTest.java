package sample.controllers;

import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.services.FileSystemService;
import sample.services.UserService;
import sample.controllers.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class RegisterControllerTest extends ApplicationTest {

    public static final String TEXT1 = "Name";
    public static final String TEXT2 = "ASDF";
    public static final String EMAIL = "email";
    public static final String PHONE = "23542354235";
    public static final String TEST_USER = "testUser000";
    public static final String TEST_PASSWORD = "testPassword";
    public static List<String> GENRES = new ArrayList<>();
    private RegisterController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();

        controller = new RegisterController();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.userBox = new CheckBox();
        controller.eventManagerBox = new CheckBox();
        controller.bandBox = new CheckBox();
        controller.registrationMessage = new Text();
        controller.textField1 = new TextField();
        controller.textField2 = new TextField();
        controller.emailField = new TextField();
        controller.phoneNumberField = new TextField();
        controller.registerButton = new Button();

        controller.textField1.setText(TEXT1);
        controller.textField2.setText(TEXT2);
        controller.emailField.setText(EMAIL);
        controller.phoneNumberField.setText(PHONE);
        controller.usernameField.setText(TEST_USER);
        controller.passwordField.setText(TEST_PASSWORD);
        controller.genres.add("Rock");
    }

    @Test
    public void registerButtonClickedTest(){
        controller.registerButtonClicked();
        assertEquals(0, UserService.getUsers().size());
    }

    @Test
    void handleBackButton() {
    }
}