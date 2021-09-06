package com.example.online_pharmacy.model.dao.impl;

import com.example.online_pharmacy.entity.Measure;
import com.example.online_pharmacy.exception.DaoException;
import com.example.online_pharmacy.model.dao.MeasureDao;
import com.example.online_pharmacy.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.example.online_pharmacy.model.dao.ColumnName.MEASURE_ID;
import static com.example.online_pharmacy.model.dao.ColumnName.MEASURE_NAME;

public class MeasureDaoImpl implements MeasureDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_ADD_MEASURE = """
            insert into measure (measure_name)
            value (?);""";

    private static final String SQL_FIND_MEASURE_BY_MEASURE_ID = """
            select measure_id, measure_name
            from measure
            where measure_id = ?;""";

    @Override
    public boolean addMeasure(Measure measure) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_MEASURE)){
            statement.setString(1, measure.getName());
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public Optional<Measure> findMeasureByMeasureId(int measureId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_MEASURE_BY_MEASURE_ID)){
            statement.setLong(1, measureId);
            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    return Optional.of(new Measure(
                            result.getLong(MEASURE_ID),
                            result.getString(MEASURE_NAME)));
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return Optional.empty();
    }
}
