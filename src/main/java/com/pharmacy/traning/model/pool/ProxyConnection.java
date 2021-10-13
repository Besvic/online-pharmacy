package com.pharmacy.traning.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * The type Proxy connection.
 */
public class ProxyConnection implements Connection {

    /**
     * The Logger.
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * The Connection.
     */
    private final Connection connection;

    /**
     * Instantiates a new Proxy connection.
     *
     * @param connection the connection
     */
    ProxyConnection(Connection connection){this.connection = connection;}

    /**
     * Create statement statement.
     *
     * @return the statement
     * @throws SQLException the sql exception
     */
    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param sql the sql
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     * Prepare call callable statement.
     *
     * @param sql the sql
     * @return the callable statement
     * @throws SQLException the sql exception
     */
    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    /**
     * Native sql string.
     *
     * @param sql the sql
     * @return the string
     * @throws SQLException the sql exception
     */
    @Override
    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    /**
     * Sets auto commit.
     *
     * @param autoCommit the auto commit
     * @throws SQLException the sql exception
     */
    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    /**
     * Gets auto commit.
     *
     * @return the auto commit
     * @throws SQLException the sql exception
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    /**
     * Commit.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * Rollback.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Close.
     */
    @Override
     public void close(){
        ConnectionPool.getInstance().releaseConnection(this);
    }

    /**
     * Really close.
     *
     * @throws SQLException the sql exception
     */
    void reallyClose() throws SQLException {
        connection.close();
    }

    /**
     * Is closed boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    /**
     * Gets meta data.
     *
     * @return the meta data
     * @throws SQLException the sql exception
     */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    /**
     * Sets read only.
     *
     * @param readOnly the read only
     * @throws SQLException the sql exception
     */
    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    /**
     * Is read only boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    /**
     * Sets catalog.
     *
     * @param catalog the catalog
     * @throws SQLException the sql exception
     */
    @Override
    public void setCatalog(String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    /**
     * Gets catalog.
     *
     * @return the catalog
     * @throws SQLException the sql exception
     */
    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    /**
     * Sets transaction isolation.
     *
     * @param level the level
     * @throws SQLException the sql exception
     */
    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    /**
     * Gets transaction isolation.
     *
     * @return the transaction isolation
     * @throws SQLException the sql exception
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    /**
     * Gets warnings.
     *
     * @return the warnings
     * @throws SQLException the sql exception
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    /**
     * Clear warnings.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    /**
     * Create statement statement.
     *
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @return the statement
     * @throws SQLException the sql exception
     */
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param sql                  the sql
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * Prepare call callable statement.
     *
     * @param sql                  the sql
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @return the callable statement
     * @throws SQLException the sql exception
     */
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * Gets type map.
     *
     * @return the type map
     * @throws SQLException the sql exception
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    /**
     * Sets type map.
     *
     * @param map the map
     * @throws SQLException the sql exception
     */
    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    /**
     * Sets holdability.
     *
     * @param holdability the holdability
     * @throws SQLException the sql exception
     */
    @Override
    public void setHoldability(int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    /**
     * Gets holdability.
     *
     * @return the holdability
     * @throws SQLException the sql exception
     */
    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    /**
     * Sets savepoint.
     *
     * @return the savepoint
     * @throws SQLException the sql exception
     */
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    /**
     * Sets savepoint.
     *
     * @param name the name
     * @return the savepoint
     * @throws SQLException the sql exception
     */
    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    /**
     * Rollback.
     *
     * @param savepoint the savepoint
     * @throws SQLException the sql exception
     */
    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    /**
     * Release savepoint.
     *
     * @param savepoint the savepoint
     * @throws SQLException the sql exception
     */
    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    /**
     * Create statement statement.
     *
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @param resultSetHoldability the result set holdability
     * @return the statement
     * @throws SQLException the sql exception
     */
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param sql                  the sql
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @param resultSetHoldability the result set holdability
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * Prepare call callable statement.
     *
     * @param sql                  the sql
     * @param resultSetType        the result set type
     * @param resultSetConcurrency the result set concurrency
     * @param resultSetHoldability the result set holdability
     * @return the callable statement
     * @throws SQLException the sql exception
     */
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param sql               the sql
     * @param autoGeneratedKeys the auto generated keys
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param sql           the sql
     * @param columnIndexes the column indexes
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param sql         the sql
     * @param columnNames the column names
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    /**
     * Create clob clob.
     *
     * @return the clob
     * @throws SQLException the sql exception
     */
    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    /**
     * Create blob blob.
     *
     * @return the blob
     * @throws SQLException the sql exception
     */
    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    /**
     * Create n clob n clob.
     *
     * @return the n clob
     * @throws SQLException the sql exception
     */
    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    /**
     * Create sqlxml sqlxml.
     *
     * @return the sqlxml
     * @throws SQLException the sql exception
     */
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    /**
     * Is valid boolean.
     *
     * @param timeout the timeout
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    /**
     * Sets client info.
     *
     * @param name  the name
     * @param value the value
     * @throws SQLClientInfoException the sql client info exception
     */
    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    /**
     * Sets client info.
     *
     * @param properties the properties
     * @throws SQLClientInfoException the sql client info exception
     */
    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    /**
     * Gets client info.
     *
     * @param name the name
     * @return the client info
     * @throws SQLException the sql exception
     */
    @Override
    public String getClientInfo(String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    /**
     * Gets client info.
     *
     * @return the client info
     * @throws SQLException the sql exception
     */
    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    /**
     * Create array of array.
     *
     * @param typeName the type name
     * @param elements the elements
     * @return the array
     * @throws SQLException the sql exception
     */
    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    /**
     * Create struct struct.
     *
     * @param typeName   the type name
     * @param attributes the attributes
     * @return the struct
     * @throws SQLException the sql exception
     */
    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    /**
     * Sets schema.
     *
     * @param schema the schema
     * @throws SQLException the sql exception
     */
    @Override
    public void setSchema(String schema) throws SQLException {
        connection.setSchema(schema);
    }

    /**
     * Gets schema.
     *
     * @return the schema
     * @throws SQLException the sql exception
     */
    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    /**
     * Abort.
     *
     * @param executor the executor
     * @throws SQLException the sql exception
     */
    @Override
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    /**
     * Sets network timeout.
     *
     * @param executor     the executor
     * @param milliseconds the milliseconds
     * @throws SQLException the sql exception
     */
    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    /**
     * Gets network timeout.
     *
     * @return the network timeout
     * @throws SQLException the sql exception
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    /**
     * Unwrap t.
     *
     * @param <T>   the type parameter
     * @param iface the iface
     * @return the t
     * @throws SQLException the sql exception
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    /**
     * Is wrapper for boolean.
     *
     * @param iface the iface
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }
}
