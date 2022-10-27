package repositories;

import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class UserRepository {
    private static final ObservableList<User> users;

    static {
        users = FXCollections.observableArrayList(UserDAO.selectAll());
    }

    private static final DateTimeFormatter logDateTimeFormatter
            = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss").withZone(
            ZoneId.of("UTC")
    );

    private static int currentUserId;

    public static Optional<User> get(Integer id) {
        for (User user : users) {
            if (user.id().equals(id)) return Optional.of(user);
        }
        return Optional.empty();
    }

    public static Optional<User> getByUsername(String username) {
        for (User user : users) {
            if (user.username().equals(username)) return Optional.of(user);
        }
        return Optional.empty();
    }

    public static Optional<User> getCurrentUser() {
        return get(currentUserId);
    }

    public static boolean authenticate(String username, String password) throws IOException {
        Instant timestamp = Instant.now();

        Optional<User> foundUser = getByUsername(username);
        if (foundUser.isPresent() && foundUser.get().checkPassword(password)) {
            System.out.println("Authenticated user: " + foundUser.get().id());
            recordLoginAttempt(username, timestamp, true);
            currentUserId = foundUser.get().id();
            return true;
        }

        recordLoginAttempt(username, timestamp, false);
        return false;
    }


    private static void recordLoginAttempt(String username, Instant timestamp, Boolean succeeded) throws IOException {
        String projectDir = System.getProperty("user.dir");
        Path logFilePath = Path.of(projectDir, "login_activity.txt");

        String timestampStr = logDateTimeFormatter.format(timestamp) + " UTC";
        String eventStr = succeeded ? "successfully logged in" : "failed to log in";

        Files.writeString(
                logFilePath,
                "User \"" + username + "\" " + eventStr + " at " + timestampStr + System.lineSeparator(),
                CREATE, APPEND
        );
    }

}
