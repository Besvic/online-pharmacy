package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Pharmacy;

import java.util.List;
import java.util.Optional;

/**
 * The interface Service pharmacy.
 */
public interface ServicePharmacy {
    /**
     * Create pharmacy boolean.
     *
     * @param pharmacy the pharmacy
     * @param number   the number
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean createPharmacy(Optional<Pharmacy> pharmacy, String number) throws ServiceException, DaoException;

    /**
     * Delete pharmacy boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean deletePharmacy(long id) throws ServiceException, DaoException;

    /**
     * Restore pharmacy boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean restorePharmacy(long id) throws ServiceException, DaoException;

    /**
     * Find all actual pharmacy list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<Pharmacy> findAllActualPharmacy() throws ServiceException, DaoException;

    /**
     * Find all pharmacy list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<Pharmacy> findAllPharmacy() throws ServiceException, DaoException;
}
