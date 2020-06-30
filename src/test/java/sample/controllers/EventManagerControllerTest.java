package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.model.Invite;
import sample.services.EventService;
import sample.services.FileSystemService;
import sample.services.InviteService;
import sample.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventManagerControllerTest extends ApplicationTest {
    public static final String EVENT_NAME = "testEventName";
    public static final String EVENT_CODE = "testEventCode";
    public static final String EVENT_DATE = "testEventDate";
    public static final String EVENT_LOCATION = "testEventLocation";
    public static final String TICKET_PRICE = "50.00";
    public static final String LIMIT = "5000";
    public static final String DESCRIPTION = "testDescription";
    public static final String BAND_NAME = "testBand";
    public static final String INVITE_DESCRIPTION = "inviteTestDescription";
    private EventManagerController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        EventService.loadEventsFromFile();
        InviteService.loadInvitesFromFile();
    }

    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        EventService.loadEventsFromFile();
        InviteService.loadInvitesFromFile();

        controller = new EventManagerController();
        controller.eventName = new TextField();
        controller.eventDate = new TextField();
        controller.eventCode = new TextField();
        controller.eventLocation = new TextField();
        controller.ticketPrice = new TextField();
        controller.limitOfParticipants = new TextField();
        controller.eventType = new TextField();
        controller.description = new TextField();
        controller.eventDetailsArea = new TextArea();
        controller.newEventButton = new Button();

        controller.eventName.setText(EVENT_NAME);
        controller.eventCode.setText(EVENT_CODE);
        controller.eventDate.setText(EVENT_DATE);
        controller.eventLocation.setText(EVENT_LOCATION);
        controller.ticketPrice.setText(TICKET_PRICE);
        controller.limitOfParticipants.setText(LIMIT);
        controller.description.setText(DESCRIPTION);
    }

    @Test
    public void testAddEvent() throws IOException {
        controller.newEventClicked(new ActionEvent());
        assertEquals(1, EventService.getEvents().size());
    }

    @Test
    public void testAddSameEventTwice() throws IOException {
        controller.newEventClicked(new ActionEvent());
        controller.newEventClicked(new ActionEvent());
        assertEquals("Error editing the event  Code already exists      Try again", controller.errorMessage.getText());
    }

    @Test
    public void testSendInvite() {
        controller.sendInviteButtonPressed(new Button(), BAND_NAME, INVITE_DESCRIPTION);
        assertEquals(1, InviteService.getInvites().size());
    }

    @Test
    public void testSendInviteToTheSameBand() {
        controller.sendInviteButtonPressed(new Button(), BAND_NAME, INVITE_DESCRIPTION);
        controller.sendInviteButtonPressed(new Button(), BAND_NAME, INVITE_DESCRIPTION);
        assertEquals("Error editing the event  Band already invited     Try again", controller.errorMessage.getText());
    }
}