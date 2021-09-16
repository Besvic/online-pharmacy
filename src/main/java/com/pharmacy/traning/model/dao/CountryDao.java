package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.Country;
import com.pharmacy.traning.exception.DaoException;

import java.util.Optional;

public interface CountryDao {

    boolean addCountry(Country country) throws DaoException;
    //boolean deleteCountryById(int countryId) throws DaoException;
    Optional<Country> findCountryByName(String countryName) throws DaoException;
}
