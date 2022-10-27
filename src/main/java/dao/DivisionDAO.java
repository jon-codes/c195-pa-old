package dao;

import models.Division;
import util.DbConnection;
import util.ErrorHandling;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DivisionDAO {

    private static final String table = "first_level_divisions";
    private static final String idColumn = "Division_ID";
    private static final String nameColumn = "Division";
    private static final String countryIdColumn = "Country_ID";

    public static List<Division> selectAll() {
        ArrayList<Division> divisions = new ArrayList<>();
        String query = "select * from " + table;

        try {
            PreparedStatement statement = DbConnection.get().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                divisions.add(createDivisionFromResultSet(resultSet));
            }
        }
        catch(SQLException e) {
            ErrorHandling.printTraceAndExit(e);
        }

        return divisions;
    }

    private static Division createDivisionFromResultSet(ResultSet resultSet) throws SQLException {
        return new Division(
                resultSet.getInt(idColumn),
                resultSet.getString(nameColumn),
                resultSet.getInt(countryIdColumn)
        );
    }
}
