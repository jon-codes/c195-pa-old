package repositories;

import dao.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Customer;

import java.util.Optional;

public class CustomerRepository {

    private static final ObservableList<Customer> customers;

    static {
        customers = FXCollections.observableList(CustomerDAO.selectAll());
    }

    public static ObservableList<Customer> list() {
        return FXCollections.unmodifiableObservableList(customers);
    }

    public static Optional<Customer> get(int id) {
        for (Customer customer : customers) {
            if (customer.id() == id) return Optional.of(customer);
        }
        return Optional.empty();
    }

    public static boolean delete(int id) {
        Optional<Customer> customer = get(id);

        if (customer.isPresent()) {
            CustomerDAO.delete(id);
            customers.removeIf(c -> c.id() == id);
            return true;
        }

        return false;
    }
}
