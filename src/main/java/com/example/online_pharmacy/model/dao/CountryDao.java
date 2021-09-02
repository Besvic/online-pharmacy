package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.Country;
import com.example.online_pharmacy.exception.DaoException;

import java.util.List;

public interface CountryDao {

    boolean addCountry(Country country) throws DaoException;
    boolean deleteCountryById(int countryId) throws DaoException;
    List<Country> findCountryByName(String countryName) throws DaoException;
}
