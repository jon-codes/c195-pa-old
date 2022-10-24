package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.User;
import util.DbConnection;
import util.ErrorHandling;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {

    private static final String table = "users";
    private static final String idColumn = "User_ID";
    private static final String usernameColumn = "User_Name";
    private static final String passwordColumn = "Password";

    private static final ObservableList<User> users;

//    public static ObservableList<User> getUsers() {
//        return FXCollections.unmodifiableObservableList(users);
//    }

    static {
        users = FXCollections.observableArrayList();
        updateUsersFromDb();
    }

    private static void updateUsersFromDb() {
        String query = "select * from " + table;

        try {
            PreparedStatement statement = DbConnection.get().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            users.clear();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            ErrorHandling.printTraceAndExit(e);
        }
    }

//    public static Optional<User> getUser(int id) {
//        for (User user : users) {
//            if (user.id() == id) return Optional.of(user);
//        }
//        return Optional.empty();
//    }

    public static Optional<User> getUserByUsername(String username) {
        for (User user : users) {
            if (user.username().equals(username)) return Optional.of(user);
        }
        return Optional.empty();
    }

    private static User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt(idColumn),
                resultSet.getString(usernameColumn),
                resultSet.getString(passwordColumn)
        );
    }

}
