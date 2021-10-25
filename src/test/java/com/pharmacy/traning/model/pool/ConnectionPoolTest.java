package com.pharmacy.traning.model.pool;

import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    private static final ConnectionPool instance = ConnectionPool.getInstance();

    @Test
    void getConnection() {
        try(Connection connection = instance.getConnection()) {
            boolean actual = connection.isValid(0);
            assertTrue(actual);
            instance.releaseConnection(connection);
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void releaseConnection() {
        try (Connection connection = instance.getConnection()){
            boolean actual = instance.releaseConnection(connection);
            assertTrue(actual);
        } catch (SQLException e) {
           fail();
        }
    }

    @AfterClass
    public static void destroyPool() {
        instance.destroyPool();
    }
}