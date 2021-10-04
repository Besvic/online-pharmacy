package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Pharmacy;

import java.util.List;
import java.util.Optional;

public interface ServicePharmacy {
    boolean createPharmacy(Optional<Pharmacy> pharmacy) throws ServiceException, DaoException;
    boolean deletePharmacy(long id) throws ServiceException, DaoException;
    boolean restorePharmacy(long id) throws ServiceException, DaoException;
    List<Pharmacy> findAllActualPharmacy() throws ServiceException, DaoException;
    List<Pharmacy> findAllPharmacy() throws ServiceException, DaoException;
}
