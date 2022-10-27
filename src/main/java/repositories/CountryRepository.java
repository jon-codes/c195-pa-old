package repositories;

import dao.CountryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Country;

import java.util.Optional;

public class CountryRepository {

    private static final ObservableList<Country> countries;

    static {
        countries = FXCollections.observableList(CountryDAO.selectAll());
    }

    public static ObservableList<Country> list() {
        return FXCollections.unmodifiableObservableList(countries);
    }

    public static Optional<Country> get(int id) {
        for (Country country : countries) {
            if (country.id() == id) return Optional.of(country);
        }
        return Optional.empty();
    }
}
