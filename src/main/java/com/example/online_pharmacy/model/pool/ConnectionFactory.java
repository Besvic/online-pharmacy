package com.example.online_pharmacy.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionFactory {

    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();


    // FIXME: 22.08.2021 delete comment
  /*  private static String DATABASE_URL;
    private static final String DB_URL = "url";
    private static final String DB_DRIVER = "driver";
    private static final String DB_PROPERTIES = "db.properties";*/

    private static final String DB_PROPERTIES = "properties/database.properties";
    private static final String PROPERTY_URL = "db.url";
    private static final String PROPERTY_USER = "db.user";
    private static final String PROPERTY_PASSWORD = "db.password";
    private static final String PROPERTY_DRIVER_CLASS_NAME = "driver.class.name";
    private static final String DB_URL;
    private static final String DB_PASSWORD;
    private static final String DB_USER;
    private static final String DB_DRIVER_CLASS_NAME;

    static {
        try(InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(DB_PROPERTIES)){
            properties.load(inputStream);
            DB_URL = properties.getProperty(PROPERTY_URL);
            DB_PASSWORD = properties.getProperty(PROPERTY_PASSWORD);
            DB_USER = properties.getProperty(PROPERTY_USER);
            DB_DRIVER_CLASS_NAME = properties.getProperty(PROPERTY_DRIVER_CLASS_NAME);
            Class.forName(DB_DRIVER_CLASS_NAME);
        } catch (FileNotFoundException e) {
            logger.error( "Not found file properties: " + DB_PROPERTIES);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Incorrect input data.");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            logger.error("Not found driver: " + PROPERTY_DRIVER_CLASS_NAME);
            throw new RuntimeException(e);
        }
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private ConnectionFactory() {
    }



}
