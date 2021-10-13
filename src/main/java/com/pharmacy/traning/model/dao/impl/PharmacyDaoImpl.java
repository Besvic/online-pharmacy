package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.PharmacyDao;
import com.pharmacy.traning.model.entity.Pharmacy;
import com.pharmacy.traning.model.entity.PharmacyStatus;
import com.pharmacy.traning.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.pharmacy.traning.model.dao.ColumnName.*;

/***
 * class PharmacyDaoImpl
 * @author Victor Besarab
 */
public class PharmacyDaoImpl implements PharmacyDao {

    private static final Logger logger = LogManager.getLogger();
    private static PharmacyDao instance;

    private static final String SQL_CREATE_PHARMACY = """
            insert into pharmacy(pharmacy_city, pharmacy_street, pharmacy_number)
            values (?, ?, ?);""";

    private static final String SQL_DELETE_PHARMACY_BY_ID = """
            update pharmacy
            set pharmacy_status = 'delete'
            where pharmacy_id = ?""";

    private static final String SQL_RESTORE_PHARMACY_BY_ID = """
            update pharmacy
            set pharmacy_status = 'actual'
            where pharmacy_id = ?""";


    private static final String SQL_FIND_ALL_ACTUAL_PHARMACY = """
            select pharmacy_id, pharmacy_city, pharmacy_street, pharmacy_number
            from pharmacy
            where pharmacy_status = 'actual';""";

    private static final String SQL_FIND_ALL_PHARMACY = """
            select pharmacy_id, pharmacy_city, pharmacy_street, pharmacy_number, pharmacy_status
            from pharmacy
            order by (case pharmacy_status 
            when 'actual' then 1
            when 'delete' then 2
            end) asc;""";

    /***
     *
     * @return pharmacy dao
     */
    public static PharmacyDao getInstance(){
        if (instance == null){
            instance = new PharmacyDaoImpl();
        }
        return instance;
    }
    private PharmacyDaoImpl(){

    }

    /***
     *
     * @param pharmacy
     * @return
     * @throws DaoException
     */
    @Override
    public boolean createPharmacy(Pharmacy pharmacy) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_PHARMACY)){
            statement.setString(1, pharmacy.getCity());
            statement.setString(2, pharmacy.getStreet());
            statement.setInt(3, pharmacy.getNumber());
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    /***
     *
     * @param pharmacyId
     * @return
     * @throws DaoException
     */
    @Override
    public boolean deletePharmacy(long pharmacyId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PHARMACY_BY_ID)){
            statement.setLong(1, pharmacyId);
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    /***
     *
     * @param pharmacyId
     * @return
     * @throws DaoException
     */
    @Override
    public boolean restorePharmacy(long pharmacyId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_RESTORE_PHARMACY_BY_ID)){
            statement.setLong(1, pharmacyId);
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    /***
     *
     * @return
     * @throws DaoException
     */
    @Override
    public List<Pharmacy> findAllActualPharmacy() throws DaoException {
        List<Pharmacy> pharmacyList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACTUAL_PHARMACY);
             ResultSet result = statement.executeQuery()) {
                while (result.next()){
                    pharmacyList.add(new Pharmacy.PharmacyBuilder()
                        .setId(result.getLong(PHARMACY_ID))
                        .setCity(result.getString(PHARMACY_CITY))
                        .setStreet(result.getString(PHARMACY_STREET))
                        .setNumber(result.getInt(PHARMACY_NUMBER))
                        .createPharmacy());
                }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return pharmacyList;
    }

    /***
     *
     * @return
     * @throws DaoException
     */
    @Override
    public List<Pharmacy> findAllPharmacy() throws DaoException {
        List<Pharmacy> pharmacyList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PHARMACY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()){
                pharmacyList.add(new Pharmacy.PharmacyBuilder()
                        .setId(result.getLong(PHARMACY_ID))
                        .setCity(result.getString(PHARMACY_CITY))
                        .setStreet(result.getString(PHARMACY_STREET))
                        .setNumber(result.getInt(PHARMACY_NUMBER))
                        .setStatus(PharmacyStatus.valueOf(result.getString(PHARMACY_STATUS).toUpperCase()))
                        .createPharmacy());
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return pharmacyList;
    }
}
