package sample.services;

import sample.exceptions.*;
import sample.model.Event;
import sample.model.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EventService {
    private static List<Event> events;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "events.json");

    public static void loadUsersFromFile() throws IOException{
        if(!Files.exists(USERS_PATH)){
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("events.json"), USERS_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();

        events = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<Event>>() {
        });
    }

    public static void addEvent(String name, String code, String date, Double ticketPrice, String location, Integer limitOfParticipants, String eventType, String description) throws EventAlreadyExistsException {
        checkEventDoesNotAlreadyExist(code);
        events.add(new Event(name, code, date, ticketPrice, location, limitOfParticipants, eventType, description));
        persistEvents();
    }

    private static void checkEventDoesNotAlreadyExist(String code) throws EventAlreadyExistsException {
        for (Event event : events) {
            if (Objects.equals(code, event.getCode()))
                throw new EventAlreadyExistsException(code);
        }
    }

    private static void persistEvents() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), events);
        } catch (IOException e) {
            throw new CouldNotWriteEventException();
        }
    }


    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
