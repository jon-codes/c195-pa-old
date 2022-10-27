package dao;

import models.Customer;
import util.DbConnection;
import util.ErrorHandling;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private static final String table = "customers";
    private static final String idColumn = "Customer_ID";
    private static final String nameColumn = "Customer_Name";
    private static final String addressColumn = "Address";
    private static final String postalCodeColumn = "Postal_Code";
    private static final String phoneColumn = "Phone";

    public static List<Customer> selectAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "select * from " + table;

        try {
            PreparedStatement statement = DbConnection.get().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customers.add(createCustomerFromResultSet(resultSet));
            }
        } catch(SQLException e) {
            ErrorHandling.printTraceAndExit(e);
        }

        return customers;
    }

    public static void delete(int id) {
        String query = "delete from " + table + " where " + idColumn + " = ?";

        try {
            PreparedStatement statement = DbConnection.get().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            ErrorHandling.printTraceAndExit(e);
        }
    }

    private static Customer createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getInt(idColumn),
                resultSet.getString(nameColumn),
                resultSet.getString(addressColumn),
                resultSet.getString(postalCodeColumn),
                resultSet.getString(phoneColumn)
        );
    }
}
