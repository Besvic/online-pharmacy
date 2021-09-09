package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.entity.Country;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.CountryDao;
import com.pharmacy.traning.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.pharmacy.traning.model.dao.ColumnName.COUNTRY_ID;
import static com.pharmacy.traning.model.dao.ColumnName.COUNTRY_NAME;

public class CountryDaoImpl implements CountryDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_ADD_COUNTRY = """
            insert into country (country_name)
            value (?);""";
    private static final String SQL_FIND_COUNTRY_BY_NAME = """
            select country_id, country_name
            from country
            where country_name = ?;""";

    @Override
    public boolean addCountry(Country country) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_COUNTRY)){
            statement.setString(1, country.getName());
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public Optional<Country> findCountryByName(String countryName) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_COUNTRY_BY_NAME)){
            statement.setString(1, countryName);
            try (ResultSet result = statement.executeQuery()){
                if (result.next())
                    return Optional.of(new Country(result.getInt(COUNTRY_ID),
                            result.getString(COUNTRY_NAME)));
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return Optional.empty();
    }
}
