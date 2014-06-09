package com.savoirtech.hecate.cql3.convert.set;

import com.datastax.driver.core.DataType;
import com.savoirtech.hecate.cql3.convert.ValueConverter;
import com.savoirtech.hecate.cql3.persistence.Dehydrator;
import com.savoirtech.hecate.cql3.persistence.Hydrator;

import java.util.HashSet;
import java.util.Set;

public class SetConverter implements ValueConverter {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final ValueConverter elementConverter;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public SetConverter(ValueConverter elementConverter) {
        this.elementConverter = elementConverter;
    }

//----------------------------------------------------------------------------------------------------------------------
// ValueConverter Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public Object fromCassandraValue(Object value, Hydrator hydrator) {
        if (value == null) {
            return null;
        }
        Set<Object> cassandraSet = (Set<Object>) value;
        Set<Object> converted = new HashSet<>();
        for (Object cassandraValue : cassandraSet) {
            converted.add(elementConverter.fromCassandraValue(cassandraValue, hydrator));
        }
        return converted;
    }

    @Override
    public DataType getDataType() {
        return DataType.set(elementConverter.getDataType());
    }

    @Override
    public Object toCassandraValue(Object value, Dehydrator dehydrator) {
        if (value == null) {
            return null;
        }
        Set<Object> cassandraValues = new HashSet<>();
        for (Object element : (Set<?>) value) {
            cassandraValues.add(elementConverter.toCassandraValue(element, dehydrator));
        }
        return cassandraValues;
    }
}
