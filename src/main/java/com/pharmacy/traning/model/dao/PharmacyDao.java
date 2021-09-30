package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.entity.Pharmacy;

import java.util.List;

public interface PharmacyDao {

    boolean createPharmacy(Pharmacy pharmacy) throws DaoException;
    boolean deletePharmacy(long id) throws DaoException;
    boolean restorePharmacy(long pharmacyId) throws DaoException;
    List<Pharmacy> findAllActualPharmacy() throws  DaoException;
    List<Pharmacy> findAllPharmacy() throws  DaoException;


}
