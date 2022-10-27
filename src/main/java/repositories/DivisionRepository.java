package repositories;

import dao.DivisionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Division;

import java.util.ArrayList;
import java.util.List;

public class DivisionRepository {
    private static final ObservableList<Division> divisions;

    static {
        divisions = FXCollections.observableArrayList(DivisionDAO.selectAll());
    }

    public static ObservableList<Division> getByCountryId(int countryId) {
        ObservableList<Division> countryDivisions = FXCollections.observableArrayList();

        for (Division division : divisions) {
            if (division.countryId() == countryId) countryDivisions.add(division);
        }

        return countryDivisions;
    }
}
