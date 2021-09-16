package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.model.entity.Manufacture;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.ManufactureDao;
import com.pharmacy.traning.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pharmacy.traning.model.dao.ColumnName.*;

public class ManufactureDaoImpl implements ManufactureDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ManufactureDaoImpl instance = new ManufactureDaoImpl();

    public static ManufactureDaoImpl getInstance(){
        return instance;
    }

    private ManufactureDaoImpl(){

    }

    private static final String SQL_ADD_MANUFACTURE = """
            insert into manufacture (manufacture_name, manufacture_email, manufacture_country_id)
            values (?, ?, ?);""";
    private static final String SQL_FIND_MANUFACTURE_BY_ID = """
            select manufacture_id, manufacture_name, manufacture_email, manufacture_country_id
            from manufacture
            where manufacture_id = ?;""";
    private static final String SQL_FIND_MANUFACTURE_BY_NAME = """
            select manufacture_id, manufacture_name, manufacture_email, manufacture_country_id
            from manufacture
            where manufacture_name = ?;""";
    private static final String SQL_CHANGE_MANUFACTURE_NAME_BY_ID = """
            update manufacture
            set manufacture_name = ?
            where manufacture_id = ?;""";

    @Override
    public boolean addManufacture(Manufacture manufacture) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_MANUFACTURE)){
            statement.setString(1, manufacture.getName());
            statement.setString(2, manufacture.getEmail());
            statement.setLong(3, manufacture.getId());
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public Optional<Manufacture> findManufactureById(int manufactureId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_MANUFACTURE_BY_ID)){
            statement.setInt(1, manufactureId);
            try (ResultSet result = statement.executeQuery()){
                if (result.next())
                    return Optional.of(new Manufacture.ManufactureBuilder()
                    .setId(result.getLong(MANUFACTURE_ID))
                    .setName(result.getString(MANUFACTURE_NAME))
                    .setEmail(result.getString(MANUFACTURE_EMAIL))
                    .setCountryId(result.getInt(MANUFACTURE_COUNTRY_ID))
                    .createManufacture());
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return Optional.empty();
    }

    @Override
    public List<Manufacture> findManufactureByName(String manufactureName) throws DaoException {
        List<Manufacture> manufactureList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_MANUFACTURE_BY_NAME)){
            statement.setString(1, manufactureName);
            try (ResultSet result = statement.executeQuery()){
                if (result.next())
                    manufactureList.add(new Manufacture.ManufactureBuilder()
                            .setId(result.getLong(MANUFACTURE_ID))
                            .setName(result.getString(MANUFACTURE_NAME))
                            .setEmail(result.getString(MANUFACTURE_EMAIL))
                            .setCountryId(result.getInt(MANUFACTURE_COUNTRY_ID))
                            .createManufacture());
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return null;
    }

    @Override
    public boolean changeManufactureNameById(String manufactureName, int manufactureId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_MANUFACTURE_NAME_BY_ID)){
            statement.setString(1, manufactureName);
            statement.setLong(2, manufactureId);
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }
}
