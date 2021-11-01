package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Besarab Victor
 * The type Transaction. Used to perform multiple dao methods as a single operation.
 */
public class Transaction {

    private static final Logger logger = LogManager.getLogger();
    private static Transaction instance;
    private Connection connection;

    private Transaction(){}

    /**
     * Get instance transaction.
     *
     * @return the transaction
     */
    public synchronized static Transaction getInstance(){
        if (instance == null){
            instance = new Transaction();
        }
        return instance;
    }

    /**
     * Init connection connection.
     *
     * @return the connection
     * @throws DaoException the dao exception
     */
    public Connection initConnection() throws DaoException {
        try {
            if (connection == null) {
                connection = ConnectionPool.getInstance().getConnection();
            }
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Function setAutoCommit isn't available. " + e);
            throw new DaoException("Function setAutoCommit isn't available. " + e);
        }
        return connection;
    }

    /**
     * Include auto commit.
     *
     * @throws DaoException the dao exception
     */
    public void includeAutoCommit() throws DaoException {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            logger.error("Function setAutoCommit isn't available. " + e);
            throw new DaoException("Function setAutoCommit isn't available. " + e);
        }
    }

    /**
     * Commit.
     *
     * @throws DaoException the dao exception
     */
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("Function commit isn't available. " + e);
            throw new DaoException("Function commit isn't available. " + e);
        }
    }

    /**
     * Rollback.
     *
     * @throws DaoException the dao exception
     */
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("Function rollback isn't available." + e);
            throw new DaoException("Function rollback isn't available." + e);
        }
    }

}
