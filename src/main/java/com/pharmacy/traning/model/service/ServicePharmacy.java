package com.pharmacy.traning.model.service;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Pharmacy;

import java.util.List;

/**
 * @author Besarab Victor
 * The interface Service pharmacy definition all general methods for validate data before using Dao.
 */
public interface ServicePharmacy {
    /**
     * Create pharmacy check input data and send to dao method.
     *
     * @param pharmacy the pharmacy
     * @param number   the number
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createPharmacy(Pharmacy pharmacy, String number) throws ServiceException;

    /**
     * Delete pharmacy check input data and send to dao method.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deletePharmacy(long id) throws ServiceException;

    /**
     * Restore pharmacy check input data and send to dao method.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean restorePharmacy(long id) throws ServiceException;

    /**
     * Find all actual pharmacy check input data and send to dao method.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Pharmacy> findAllActualPharmacy() throws ServiceException;

    /**
     * Find all pharmacy check input data and send to dao method.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Pharmacy> findAllPharmacy() throws ServiceException;
}
