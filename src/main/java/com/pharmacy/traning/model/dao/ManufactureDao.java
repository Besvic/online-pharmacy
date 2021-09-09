package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.entity.Manufacture;
import com.pharmacy.traning.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ManufactureDao {

    boolean addManufacture(Manufacture manufacture) throws DaoException;
    Optional<Manufacture> findManufactureById(int manufactureId) throws DaoException;
    List<Manufacture> findManufactureByName(String manufactureName) throws DaoException;
    boolean changeManufactureNameById(String manufactureName, int manufactureId) throws DaoException;

}
