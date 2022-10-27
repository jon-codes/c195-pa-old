package repositories;

import dao.DivisionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Division;

import java.util.Optional;

public class DivisionRepository {

    private static final ObservableList<Division> divisions;

    static {
        divisions = FXCollections.observableArrayList(DivisionDAO.selectAll());
    }

    public static ObservableList<Division> getByCountryId(Integer countryId) {
        ObservableList<Division> countryDivisions = FXCollections.observableArrayList();

        for (Division division : divisions) {
            if (division.countryId().equals(countryId)) countryDivisions.add(division);
        }

        return countryDivisions;
    }

    public static Optional<Division> get(Integer id) {
        for (Division division : divisions) {
            if (division.id().equals(id)) return Optional.of(division);
        }
        return Optional.empty();
    }
}
