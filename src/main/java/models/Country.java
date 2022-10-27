package models;

import javafx.collections.ObservableList;
import repositories.DivisionRepository;

public record Country(int id, String name) {

    public ObservableList<Division> getDivisions() {
        return DivisionRepository.getByCountryId(this.id);
    }
}
