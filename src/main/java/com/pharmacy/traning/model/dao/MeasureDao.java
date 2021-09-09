package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.entity.Measure;
import com.pharmacy.traning.exception.DaoException;

import java.util.Optional;

public interface MeasureDao {
    boolean addMeasure(Measure measure) throws DaoException;
    Optional<Measure> findMeasureByMeasureId(int measureId) throws DaoException;
}
