package com.pharmacy.traning.model.pool;

import com.pharmacy.traning.exception.DatabaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();

    private static ConnectionPool instance = null;
    private final BlockingDeque<ProxyConnection> freeConnection;
    private final BlockingDeque<ProxyConnection> busyConnection;
    private static final Lock lock = new ReentrantLock(true);
    private static final AtomicBoolean isCreate = new AtomicBoolean(false);

    private final int DEFAULT_SIZE_CONNECTION = 5;

    public static ConnectionPool getInstance(){
        if (!isCreate.get()){
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreate.set(true);
                }
            }
            finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private ConnectionPool() {
        freeConnection = new LinkedBlockingDeque<>(DEFAULT_SIZE_CONNECTION);
        busyConnection = new LinkedBlockingDeque<>(DEFAULT_SIZE_CONNECTION);
        for (int i = 0; i < DEFAULT_SIZE_CONNECTION; i++) {
            try {
                Connection connection = ConnectionFactory.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnection.put(proxyConnection);
            } catch (InterruptedException | DatabaseException e) {
                logger.fatal("No connection to the database.");
            }
        }
        logger.info("ConnectionPool was create.");
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        if (freeConnection.size() > 0){
            try {
                connection = freeConnection.take();
                busyConnection.put(connection);
            } catch (InterruptedException e) {
                logger.error("Connection not available.", e);
            }
        } else {
            logger.info("No free connections.");
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && busyConnection.remove(connection)) {
            try {
                freeConnection.put((ProxyConnection) connection);
                return true;
            } catch (InterruptedException e) {
                logger.error("Connection not available.", e);
            }
        }
        return false;
    }

    public void destroyPool() {
        for (int i = 0; i < freeConnection.size(); i++) {
            try {
                freeConnection.take().reallyClose();
            } catch (SQLException | InterruptedException throwables) {
                logger.error("Connection not available for destroy." + throwables);
            }
        }
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
               logger.error("No driver deregister." + e);
            }
        });
    }
}
