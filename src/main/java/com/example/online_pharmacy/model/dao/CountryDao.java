package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.Country;
import com.example.online_pharmacy.exception.DaoException;

import java.nio.file.LinkOption;
import java.util.List;
import java.util.Optional;

public interface CountryDao {

    boolean addCountry(Country country) throws DaoException;
    //boolean deleteCountryById(int countryId) throws DaoException;
    Optional<Country> findCountryByName(String countryName) throws DaoException;
}
