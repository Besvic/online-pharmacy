package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.entity.Pharmacy;

import java.util.List;

/**
 * @author Besarab Victor
 * The interface Pharmacy dao find data in database.
 */
public interface PharmacyDao {

    /**
     * Create pharmacy boolean.
     *
     * @param pharmacy the pharmacy
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createPharmacy(Pharmacy pharmacy) throws DaoException;

    /**
     * Delete pharmacy boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deletePharmacy(long id) throws DaoException;

    /**
     * Restore pharmacy boolean.
     *
     * @param pharmacyId the pharmacy id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean restorePharmacy(long pharmacyId) throws DaoException;

    /**
     * Find all actual pharmacy list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Pharmacy> findAllActualPharmacy() throws  DaoException;

    /**
     * Find all pharmacy list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Pharmacy> findAllPharmacy() throws  DaoException;


}
