package com.woopra.jdbc.driver;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class WoopraConnection implements Connection {

    private final String url;
    private final Properties info;

    /**
     *
     * @param url
     * @param info
     * @throws SQLException
     */
    public WoopraConnection(String url, Properties info) throws SQLException {
        this.url = url;
        this.info = info;

        for (String k : new String[]{"user", "password"}) {
            if (this.info.get(k) == null) {
                throw new SQLException("'" + k + "' is required in connection config!");
            }
        }
        if (this.info.get("api.version") == null) {
            this.info.setProperty("api.version", "2.4");
        }

        String project = this.url.substring(WoopraDriver.URL_PREFIX.length());

        this.info.put("project", project);
    }

    /**
     *
     * @return
     */
    public Properties getProperties() {
        return this.info;
    }

    /**
     *
     * @return
     */
    public Locale getLocale() {
        return Locale.US;
    }

    /**
     *
     * @return
     */
    public String getTimeZoneName() {
        return "PST";
    }

    /**
     *
     * @return
     */
    public String getTimestampFormat() {
        return "yyyy-MM-dd hh:mm:ss";
    }

    public String getTimeFormat() {
        return "yyyy-MM-dd hh:mm:ss";
    }

    public String getDateFormat() {
        return "yyyy-MM-dd hh:mm:ss";
    }

    @Override
    public Statement createStatement() throws SQLException {
        return new WoopraStatement(this, ResultSet.TYPE_FORWARD_ONLY);
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new WoopraPreparedStatement(this, sql, ResultSet.TYPE_FORWARD_ONLY);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return false;
    }

    @Override
    public void commit() throws SQLException {
    }

    @Override
    public void rollback() throws SQLException {
    }

    @Override
    public void close() throws SQLException {
    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return new WoopraDatabaseMetaData(this);
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {

    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return true;
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
    }

    @Override
    public String getCatalog() throws SQLException {
        return null;
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return Connection.TRANSACTION_NONE;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException {
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return new WoopraStatement(this, resultSetType);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return new WoopraPreparedStatement(this, sql, resultSetType);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
    }

    @Override
    public int getHoldability() throws SQLException {
        return ResultSet.HOLD_CURSORS_OVER_COMMIT;
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new WoopraStatement(this, resultSetType);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new WoopraPreparedStatement(this, sql, resultSetType);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return new WoopraPreparedStatement(this, sql, ResultSet.TYPE_FORWARD_ONLY);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Clob createClob() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Blob createBlob() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NClob createNClob() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return true;
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return null;
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSchema() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
