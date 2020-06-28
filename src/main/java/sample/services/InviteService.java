package sample.services;

import sample.exceptions.*;
import sample.model.Event;
import sample.model.Invite;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import sample.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class InviteService {
    private static List<User> users = UserService.getUsers();
    private static List<Invite> invites;
    private static final Path INVITES_PATH = FileSystemService.getPathToFile("config", "invites.json");

    public static void loadInvitesFromFile() throws IOException{
        if(!Files.exists(INVITES_PATH)){
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("invites.json"), INVITES_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();

        invites = objectMapper.readValue(INVITES_PATH.toFile(), new TypeReference<List<Invite>>() {
        });
    }

    public static void newInvite(Event event, String bandName, String details) throws BandDoesNotExistException, BandAlreadyInvitedException {
        checkBand(bandName);
        checkEvent(event, bandName);
        invites.add(new Invite(event, bandName, details));
        //System.out.println(event);
        persistInvites();
    }

    private static void checkBand(String bandName) throws BandDoesNotExistException {
        for (User user : users) {
            if (user.getUserType().equals("Band")) {
                if (Objects.equals(bandName, user.getText1()))
                    return;
            }
        }
        throw new BandDoesNotExistException(bandName);
    }

    public static void checkEvent(Event event, String bandName) throws BandAlreadyInvitedException {
        if (event.getBands().contains(bandName)) throw new BandAlreadyInvitedException(bandName);
        for (Invite invite : invites) {
            if(invite.getEvent().getCode().equals(event.getCode()))
                if (invite.getBandName().equals(bandName))
                    throw new BandAlreadyInvitedException(bandName);
        }
    }

    public static void persistInvites() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(INVITES_PATH.toFile(), invites);
        } catch (IOException e) {
            throw new CouldNotWriteEventException();
        }
    }

    public static List<Invite> getInvites() { return invites; }
}

