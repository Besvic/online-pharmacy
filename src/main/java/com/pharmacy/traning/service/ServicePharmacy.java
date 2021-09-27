package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Pharmacy;

import java.util.List;

public interface ServicePharmacy {
    boolean createPharmacy(Pharmacy pharmacy) throws ServiceException;
    boolean deletePharmacy(long id) throws ServiceException;
    List<Pharmacy> findAllActualPharmacy() throws ServiceException, DaoException;
}
