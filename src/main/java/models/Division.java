package models;

import repositories.CountryRepository;

import java.util.Optional;

public record Division(Integer id, String name, Integer countryId) {

    public Optional<Country> getCountry() {
        return CountryRepository.get(countryId);
    }
}
