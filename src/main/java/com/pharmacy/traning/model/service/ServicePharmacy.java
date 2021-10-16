package com.pharmacy.traning.model.service;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Pharmacy;

import java.util.List;

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
     */
    boolean createPharmacy(Pharmacy pharmacy, String number) throws ServiceException;

    /**
     * Delete pharmacy boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deletePharmacy(long id) throws ServiceException;

    /**
     * Restore pharmacy boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean restorePharmacy(long id) throws ServiceException;

    /**
     * Find all actual pharmacy list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Pharmacy> findAllActualPharmacy() throws ServiceException;

    /**
     * Find all pharmacy list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Pharmacy> findAllPharmacy() throws ServiceException;
}
