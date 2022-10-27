package repositories;

import dao.CountryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Country;

public class CountryRepository {

    private static final ObservableList<Country> countries;

    static {
        countries = FXCollections.observableList(CountryDAO.selectAll());
    }

    public static ObservableList<Country> list() {
        return FXCollections.unmodifiableObservableList(countries);
    }
}
