package sample.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sample.exceptions.EventAlreadyExistsException;
import sample.model.Event;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventServiceTest {

    @BeforeClass
    public static void setupClass() {
        FileSystemService.APPLICATION_FOLDER = ".test-musicevents";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
    }

    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        EventService.loadEventsFromFile();
        assertTrue(Files.exists(EventService.EVENTS_PATH));
    }

    @Test
    public void testLoadEventsFromFile() throws Exception {
        EventService.loadEventsFromFile();
        assertNotNull(EventService.events);
        assertEquals(0, EventService.events.size());
    }

   @Test
    public void testAddOneEvent() throws Exception {
        EventService.loadEventsFromFile();
       EventService.addEvent("test", "testPass", "432", "2020",10.0,"testLoc",10,"testType","testd", null);
        assertNotNull(EventService.events);
        assertEquals(1, EventService.events.size());
    }

    @Test
    public void testAddTwoEvents() throws Exception {
        EventService.loadEventsFromFile();
        EventService.addEvent("test", "testPass", "432", "2020",10.0,"testLoc",10,"testType","testd", null);
        EventService.addEvent("test1", "testPass1", "123", "2020",10.0,"testLoc",10,"testType","testd", null);
        assertNotNull(EventService.events);
        assertEquals(2, EventService.events.size());
    }

   @Test(expected = EventAlreadyExistsException.class)
    public void testAddEventAlreadyExists() throws Exception {
        EventService.loadEventsFromFile();
       EventService.addEvent("test1", "testPass1", "123", "2020",10.0,"testLoc",10,"testType","testd", null);
       assertNotNull(EventService.events);
        EventService.checkEventDoesNotAlreadyExist("123");
    }

   @Test
    public void testAddOneEventIsPersisted() throws Exception {
        EventService.loadEventsFromFile();
       EventService.addEvent("test", "testPass", "432", "2020",10.0,"testLoc",10,"testType","testd", null);
       List<Event> events = new ObjectMapper().readValue(EventService.EVENTS_PATH.toFile(), new TypeReference<List<Event>>() {
       });
        assertNotNull(events);
        assertEquals(1, events.size());
    }

   @Test
    public void testAddTwoUserArePersisted() throws Exception {
       EventService.loadEventsFromFile();
       EventService.addEvent("test1", "testPass1", "432", "2020",10.0,"testLoc",10,"testType","testd", null);
       EventService.addEvent("test2", "testPass2", "422", "2020",10.0,"testLoc",10,"testType","testd", null);
       List<Event> events = new ObjectMapper().readValue(EventService.EVENTS_PATH.toFile(), new TypeReference<List<Event>>() {
       });
       assertNotNull(events);
       assertEquals(2, events.size());
    }

}