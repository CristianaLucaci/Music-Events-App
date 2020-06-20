package sample.services;

import  java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    private static final String APPLICATION_FOLDER = ".MusicEventsApplication";
    private static final String USER_FOLDER = System.getProperty("user.home");
    private static final String EVENT_FOLDER = System.getProperty("event.home");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }
}
