package com.savoirtech.hecate.cql3.util;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Row;
import com.savoirtech.hecate.cql3.HecateException;

public class CassandraUtils {
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static Object getValue(Row row, int columnIndex, DataType dataType) {
        switch (dataType.getName()) {
            case ASCII:
            case VARCHAR:
            case TEXT:
                return row.getString(columnIndex);
            case BIGINT:
                return row.getLong(columnIndex);
            case BOOLEAN:
                return row.getBool(columnIndex);
            case DECIMAL:
                return row.getDecimal(columnIndex);
            case DOUBLE:
                return row.getDouble(columnIndex);
            case FLOAT:
                return row.getFloat(columnIndex);
            case INT:
                return row.getInt(columnIndex);
            case TIMESTAMP:
                return row.getDate(columnIndex);
            case UUID:
                return row.getUUID(columnIndex);
            case LIST:
                return row.getList(columnIndex, typeArgument(dataType, 0));
            case SET:
                return row.getSet(columnIndex, typeArgument(dataType, 0));
            case MAP:
                return row.getMap(columnIndex, typeArgument(dataType, 0), typeArgument(dataType, 1));
            case BLOB:
                return row.getBytes(columnIndex);
            case COUNTER:
                return row.getLong(columnIndex);
            case INET:
                return row.getInet(columnIndex);
            case VARINT:
                return row.getVarint(columnIndex);
            default:
                throw new HecateException(String.format("Unsupported data type %s.", dataType.getName()));
        }
    }

    private static Class<?> typeArgument(DataType dataType, int index) {
        return dataType.getTypeArguments().get(index).asJavaClass();
    }
}
