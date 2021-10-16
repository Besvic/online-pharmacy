package com.pharmacy.traning.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Besarab Victor
 * The type Connection factory.
 */
class ConnectionFactory {

    /**
     * The Logger.
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * The Properties.
     */
    private static final Properties properties = new Properties();
    /**
     * The Database properties.
     */
    private static final String DATABASE_PROPERTIES = "db.properties";
    /**
     * The Property url.
     */
    private static final String PROPERTY_URL = "db.url";
    /**
     * The Property user.
     */
    private static final String PROPERTY_USER = "db.user";
    /**
     * The Property password.
     */
    private static final String PROPERTY_PASSWORD = "db.password";
    /**
     * The Property driver class name.
     */
    private static final String PROPERTY_DRIVER_CLASS_NAME = "driver.class.name";
    /**
     * The Url.
     */
    private static final String URL;
    /**
     * The Password.
     */
    private static final String PASSWORD;
    /**
     * The User.
     */
    private static final String USER;
    /**
     * The Driver class name.
     */
    private static final String DRIVER_CLASS_NAME;

    static {
        try(InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)){
            properties.load(inputStream);
            URL = properties.getProperty(PROPERTY_URL);
            PASSWORD = properties.getProperty(PROPERTY_PASSWORD);
            USER = properties.getProperty(PROPERTY_USER);
            DRIVER_CLASS_NAME = properties.getProperty(PROPERTY_DRIVER_CLASS_NAME);
            Class.forName(DRIVER_CLASS_NAME);
        } catch (FileNotFoundException e) {
            logger.log(Level.FATAL, "File with properties" + DATABASE_PROPERTIES + " not found " + e);
            throw new RuntimeException("File with properties" + DATABASE_PROPERTIES + " not found: " + e);
        } catch (IOException e) {
            logger.log(Level.FATAL, "Reading error: ", e);
            throw new RuntimeException("Reading error: ", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Driver " + PROPERTY_DRIVER_CLASS_NAME + "not found ", e);
            throw new RuntimeException("Driver " + PROPERTY_DRIVER_CLASS_NAME + "not found ", e);
        }
    }

    private ConnectionFactory() {
    }

    /**
     * Create connection connection.
     *
     * @return the connection
     * @throws SQLException the sql exception
     */
    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
