package services;

import dao.UserDAO;
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

public class LoginService {

    private final DateTimeFormatter logDateTimeFormatter
            = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss").withZone(
            ZoneId.of("UTC")
    );

    public boolean authenticate(String username, String password) throws IOException {
        Instant timestamp = Instant.now();

        Optional<User> foundUser = UserDAO.getUserByUsername(username);
        if (foundUser.isPresent() && foundUser.get().checkPassword(password)) {
            System.out.println("Authenticated user: " + foundUser.get().id());
            recordLoginAttempt(username, timestamp, true);
            return true;
        }

        recordLoginAttempt(username, timestamp, false);
        return false;
    }

    private void recordLoginAttempt(String username, Instant timestamp, Boolean succeeded) throws IOException {
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
