package sample.services;

import sample.exceptions.*;
import sample.model.Event;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventService {
    private static List<Event> events;
    private static final Path EVENTS_PATH = FileSystemService.getPathToFile("config", "events.json");

    public static void loadEventsFromFile() throws IOException{
        if(!Files.exists(EVENTS_PATH)){
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("events.json"), EVENTS_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();

        events = objectMapper.readValue(EVENTS_PATH.toFile(), new TypeReference<List<Event>>() {
        });
    }

    public static void addEvent(String eventManager, String name, String code, String date, Double ticketPrice, String location, Integer limitOfParticipants, String eventType, String description, ArrayList<String> bands) throws EventAlreadyExistsException {
        checkEventDoesNotAlreadyExist(code);
        events.add(new Event(eventManager, name, code, date, ticketPrice, location, limitOfParticipants, eventType, description, bands));
        //System.out.println(events);
        persistEvents();
    }

    private static void checkEventDoesNotAlreadyExist(String code) throws EventAlreadyExistsException {
        for (Event event : events) {
            if (Objects.equals(code, event.getCode()))
                throw new EventAlreadyExistsException(code);
        }
    }

    public static void persistEvents() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(EVENTS_PATH.toFile(), events);
        } catch (IOException e) {
            throw new CouldNotWriteEventException();
        }
    }

    public static List<Event> getEvents() { return events; }
}
