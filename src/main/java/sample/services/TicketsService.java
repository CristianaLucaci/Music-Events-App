package sample.services;

import sample.exceptions.*;
import sample.model.Event;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import sample.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class TicketsService {
    private static List<User> users = UserService.getUsers();
    private static List<Event> tickets;
    private static final Path TICKETS_PATH = FileSystemService.getPathToFile("config", "tickets.json");

    public static void loadTicketsFromFile() throws IOException{
        if(!Files.exists(TICKETS_PATH)){
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("tickets.json"),TICKETS_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();

        tickets = objectMapper.readValue(TICKETS_PATH.toFile(), new TypeReference<List<Event>>() {
        });
    }

    public static void newTicket(Event event)throws TicketAlreadyBoughtException{
        checkEvent(event);
        tickets.add(event);
        //System.out.println(event);
        persistTickets();
    }

    public static void checkEvent(Event event) throws TicketAlreadyBoughtException {
        for (Event ev : tickets) {
            if (ev.getName().equals(event.getName()))
                throw new TicketAlreadyBoughtException(event.getName());
        }
    }

    public static void persistTickets() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(TICKETS_PATH.toFile(), tickets);
        } catch (IOException e) {
            throw new CouldNotWriteEventException();
        }
    }

    public static List<Event> getTickets() { return tickets; }
}
