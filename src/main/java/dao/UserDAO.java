package dao;

import models.User;
import util.DbConnection;
import util.ErrorHandling;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final String table = "users";
    private static final String idColumn = "User_ID";
    private static final String usernameColumn = "User_Name";
    private static final String passwordColumn = "Password";

    public static List<User> selectAll() {
        ArrayList<User> users = new ArrayList<>();
        String query = "select * from " + table;

        try {
            PreparedStatement statement = DbConnection.get().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            ErrorHandling.printTraceAndExit(e);
        }

        return users;
    }

    private static User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt(idColumn),
                resultSet.getString(usernameColumn),
                resultSet.getString(passwordColumn)
        );
    }
}
