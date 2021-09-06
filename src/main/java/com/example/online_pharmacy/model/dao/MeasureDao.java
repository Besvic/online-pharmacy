package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.Measure;
import com.example.online_pharmacy.exception.DaoException;

import java.util.Optional;

public interface MeasureDao {
    boolean addMeasure(Measure measure) throws DaoException;
    Optional<Measure> findMeasureByMeasureId(int measureId) throws DaoException;
}
