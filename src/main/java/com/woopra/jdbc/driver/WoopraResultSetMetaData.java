package com.woopra.jdbc.driver;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WoopraResultSetMetaData implements ResultSetMetaData {

    private final List<String> columnNames, columnTitles, columnTypes;

    /**
     *
     * @param columnNames
     * @param columnTitles
     * @param columnTypes
     */
    WoopraResultSetMetaData(List<String> columnNames, List<String> columnTitles, List<String> columnTypes) {
        this.columnNames = columnNames;
        this.columnTitles = columnTitles;
        this.columnTypes = columnTypes;
    }

    @Override
    public String getColumnClassName(int column) throws SQLException {
        return this.columnTypes.get(column - 1);
    }

    @Override
    public int getColumnCount() throws SQLException {
        return columnNames.size();
    }

    @Override
    public String getCatalogName(int column) throws SQLException {
        return "";
    }

    @Override
    public int getColumnDisplaySize(int column) throws SQLException {
        return 20;
    }

    @Override
    public boolean isAutoIncrement(int column) throws SQLException {
        return false;
    }

    @Override
    public boolean isCaseSensitive(int column) throws SQLException {
        // all columns are uppercase
        return false;
    }

    @Override
    public boolean isSearchable(int column) throws SQLException {
        // the implementation doesn't support the where clause
        return false;
    }

    @Override
    public boolean isCurrency(int column) throws SQLException {
        return false;
    }

    @Override
    public int isNullable(int column) throws SQLException {
        return ResultSetMetaData.columnNullableUnknown;
    }

    @Override
    public boolean isSigned(int column) throws SQLException {
        return false;
    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        // SQL column numbers start at 1
        return this.columnTitles.get(column);
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        // SQL column numbers start at 1
        return this.columnNames.get(column);
    }

    @Override
    public String getSchemaName(int column) throws SQLException {
        return "";
    }

    @Override
    public int getPrecision(int column) throws SQLException {
        // All the fields are text, should this throw an SQLException?
        return 0;
    }

    @Override
    public int getScale(int column) throws SQLException {
        // All the fields are text, should this throw an SQLException?
        return 0;
    }

    @Override
    public String getTableName(int column) throws SQLException {
        return "-";
    }

    private Map<String, Integer> typeNameToTypeCode = new HashMap<String, Integer>() {

        {
            put("String", new Integer(Types.VARCHAR));
            put("Boolean", new Integer(Types.BOOLEAN));
            put("Byte", new Integer(Types.TINYINT));
            put("Short", new Integer(Types.SMALLINT));
            put("Int", new Integer(Types.INTEGER));
            put("Integer", new Integer(Types.INTEGER));
            put("Long", new Integer(Types.BIGINT));
            put("Float", new Integer(Types.FLOAT));
            put("Double", new Integer(Types.DOUBLE));
            put("BigDecimal", new Integer(Types.DECIMAL));
            put("Date", new Integer(Types.DATE));
            put("Time", new Integer(Types.TIME));
            put("Timestamp", new Integer(Types.TIMESTAMP));
            put("Blob", new Integer(Types.BLOB));
            put("Clob", new Integer(Types.CLOB));
            put("expression", new Integer(Types.BLOB));
        }
    };

    @Override
    public int getColumnType(int column) throws SQLException {
        String columnTypeName = getColumnTypeName(column);
        return typeNameToTypeCode.get(columnTypeName);
    }

    @Override
    public String getColumnTypeName(int column) throws SQLException {
        return columnTypes.get(column);
    }

    @Override
    public boolean isReadOnly(int column) throws SQLException {
        return true;
    }

    @Override
    public boolean isWritable(int column) throws SQLException {
        return false;
    }

    @Override
    public boolean isDefinitelyWritable(int column) throws SQLException {
        return false;
    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
