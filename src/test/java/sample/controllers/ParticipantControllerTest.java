package sample.controllers;

import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.platform.engine.support.descriptor.FileSystemSource;
import org.testfx.framework.junit.ApplicationTest;
import sample.model.User;
import sample.services.FileSystemService;
import sample.services.UserService;

import java.nio.file.FileSystem;

import static org.junit.Assert.*;

public class ParticipantControllerTest extends ApplicationTest {
    public static final String TEST_USER = "testUser";
    private ParticipantController controller;

    @BeforeClass
    public static void setupClass() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".test-musicevents";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();

        controller = new ParticipantController();
        controller.text= new Text();
        controller.currentUser=new User();
        controller.moneyInput=new TextField();

        controller.currentUser.setText2(TEST_USER);
        controller.text.setText(TEST_USER);
        controller.moneyInput.setText("10");
    }


    @Test
    public void testAddMoney(){
        controller.addMoney(controller.moneyInput, controller.moneyInput.getText());
        assertEquals(10,controller.getVar());
        assertEquals(" 10",controller.printVar());
    }

    @Test
    public void testRetrieveMoney(){
        controller.retrieveMoney(controller.moneyInput, controller.moneyInput.getText());
        assertEquals(-10,controller.getVar());
        assertEquals(" -10",controller.printVar());
    }
}