package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.Units;
import com.example.online_pharmacy.exception.DaoException;

import java.util.List;

public interface UnitsDao {
    boolean addUnits(Units units) throws DaoException;
    List<Units> findUnitsByUnitsId(int unitsId) throws DaoException;
}
