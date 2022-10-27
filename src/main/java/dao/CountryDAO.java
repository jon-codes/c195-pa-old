package dao;

import models.Country;
import util.DbConnection;
import util.ErrorHandling;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {

    private static final String table = "countries";
    private static final String idColumn = "Country_ID";
    private static final String nameColumn = "Country";

    public static List<Country> selectAll() {
        ArrayList<Country> countries = new ArrayList<>();
        String query = "select * from " + table;

        try {
            PreparedStatement statement = DbConnection.get().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                countries.add(createCountryFromResultSet(resultSet));
            }
        }
        catch(SQLException e) {
            ErrorHandling.printTraceAndExit(e);
        }

        return countries;
    }

    private static Country createCountryFromResultSet(ResultSet resultSet) throws SQLException {
        return new Country(
                resultSet.getInt(idColumn),
                resultSet.getString(nameColumn)
        );
    }
}
