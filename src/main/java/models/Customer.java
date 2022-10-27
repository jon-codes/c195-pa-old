package models;

import repositories.DivisionRepository;

import java.util.Optional;

public record Customer(Integer id, String name, String address, String postalCode, String phone, Integer divisionId) {

    public Optional<Division> getDivision() {
        return DivisionRepository.get(divisionId);
    }

    public Optional<Country> getCountry() {
        return getDivision().flatMap(Division::getCountry);
    }
}
