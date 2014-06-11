package com.savoirtech.hecate.cql3.handler;

import com.datastax.driver.core.DataType;
import com.savoirtech.hecate.cql3.handler.context.DeleteContext;
import com.savoirtech.hecate.cql3.handler.context.QueryContext;
import com.savoirtech.hecate.cql3.handler.context.SaveContext;
import com.savoirtech.hecate.cql3.util.InjectionTarget;

public interface ColumnHandler {
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    DataType getColumnType();

    void getDeletionIdentifiers(Object columnValue, DeleteContext context);

    void injectFacetValue(InjectionTarget<Object> target, Object columnValue, QueryContext context);

    Object getInsertValue(Object facetValue, SaveContext context);

    Object getWhereClauseValue(Object parameterValue);

    boolean isCascading();
}
