package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.Manufacture;
import com.example.online_pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ManufactureDao {

    boolean addManufacture(Manufacture manufacture) throws DaoException;
    Optional<Manufacture> findManufactureById(int manufactureId) throws DaoException;
    List<Manufacture> findManufactureByName(String manufactureName) throws DaoException;
    boolean changeManufactureNameById(String manufactureName, int manufactureId) throws DaoException;

}
