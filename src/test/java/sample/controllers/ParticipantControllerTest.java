package sample.controllers;

import javafx.application.Application;
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

        controller.currentUser.setText2(TEST_USER);
        controller.text.setText(TEST_USER);
    }

    @Test
    public void testInitialize(){
        controller.initialize();
        assertFalse(controller.text.equals(TEST_USER));
    }
}