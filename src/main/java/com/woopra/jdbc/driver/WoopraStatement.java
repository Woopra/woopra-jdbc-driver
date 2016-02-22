package com.woopra.jdbc.driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.Statement;

public class WoopraStatement implements Statement {

    private WoopraConnection connection;
    private int maxRows = 1;
    private int fetchSize = 1;
    private int fetchDirection = ResultSet.FETCH_FORWARD;
    protected int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
    private boolean closed = false;

    protected WoopraStatement(WoopraConnection connection, int resultSetType) {

        this.connection = connection;
        this.resultSetType = resultSetType;
    }

    protected void checkOpen() throws SQLException {
        if (closed) {
            throw new SQLException();
        }
    }

    @Override
    public void setMaxFieldSize(int p0) throws SQLException {
        throw new SQLException();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        checkOpen();

        maxRows = max;
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
    }

    @Override
    public void setCursorName(String name) throws SQLException {
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        checkOpen();

//        this.fetchSize = rows;
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return 60;
    }

    @Override
    public int getMaxRows() throws SQLException {
        checkOpen();

        return maxRows;
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        checkOpen();

        return 0;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        checkOpen();

        return null;
    }

    ResultSet rs;

    @Override
    public ResultSet getResultSet() throws SQLException {
        checkOpen();

        return rs;
    }

    @Override
    public int getUpdateCount() throws SQLException {
        checkOpen();

        /*
         * Driver is read-only, so no updates are possible.
         */
        return -1;
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        checkOpen();

        return true;
    }

    @Override
    public int getFetchDirection() throws SQLException {
        checkOpen();

        return fetchDirection;
    }

    @Override
    public int getFetchSize() throws SQLException {
        checkOpen();

        return fetchSize;
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        checkOpen();

        return ResultSet.CONCUR_READ_ONLY;
    }

    @Override
    public int getResultSetType() throws SQLException {
        checkOpen();

        return this.resultSetType;
    }

    @Override
    public Connection getConnection() throws SQLException {
        checkOpen();

        return connection;
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return new WoopraResultSet(this.connection, sql);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void close() throws SQLException {
        closed = true;
    }

    @Override
    public void cancel() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void clearWarnings() throws SQLException {
        checkOpen();
    }

    @Override
    public boolean execute(String sql) throws SQLException {

        return true;
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void clearBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    // ---------------------------------------------------------------------
    // JDBC 3.0
    // ---------------------------------------------------------------------
    @Override
    public boolean getMoreResults(int current) throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys)
            throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }

    @Override
    public int executeUpdate(String sql, String[] columnNames)
            throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }

    @Override
    public boolean execute(String sql, String[] columnNames)
            throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }

    @Override
    public boolean isClosed() throws SQLException {
        return closed;
    }

    @Override
    public boolean isPoolable() throws SQLException {
        checkOpen();

        return false;
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        checkOpen();
    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        return null;
    }

    public boolean isCloseOnCompletion() throws SQLException {
        checkOpen();

        return false;
    }

    public void closeOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException();

    }
}