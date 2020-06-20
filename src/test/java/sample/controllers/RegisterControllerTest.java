package sample.controllers;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.services.FileSystemService;
import sample.services.UserService;

import static org.junit.jupiter.api.Assertions.*;

class RegisterControllerTest extends ApplicationTest {

    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    private RegisterController controller;

    

    @Test
    void registerButtonClicked() {
    }

    @Test
    void handleBackButton() {
    }
}