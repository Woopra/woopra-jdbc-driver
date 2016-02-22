package com.woopra.jdbc.driver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class WoopraDriver implements Driver {

    public final static String URL_PREFIX = "jdbc:woopra://";

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 1;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {

        if (!url.startsWith(URL_PREFIX)) {
            return null;
        }

        return new WoopraConnection(url, info);

    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith(URL_PREFIX);
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

    static {
        try {
            DriverManager.registerDriver(new WoopraDriver());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
