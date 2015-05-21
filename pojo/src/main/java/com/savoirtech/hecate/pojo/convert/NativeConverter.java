/*
 * Copyright (c) 2012-2015 Savoir Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.savoirtech.hecate.pojo.convert;

import com.datastax.driver.core.DataType;

public final class NativeConverter implements Converter {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final Converter BOOLEAN = new NativeConverter(DataType.cboolean());
    public static final Converter DATE = new NativeConverter(DataType.timestamp());
    public static final Converter DOUBLE = new NativeConverter(DataType.cdouble());
    public static final Converter FLOAT = new NativeConverter(DataType.cfloat());
    public static final Converter INTEGER = new NativeConverter(DataType.cint());
    public static final Converter LONG = new NativeConverter(DataType.bigint());
    public static final Converter UUID = new NativeConverter(DataType.uuid());
    public static final Converter STRING = new NativeConverter(DataType.varchar());
    public static final Converter INET = new NativeConverter(DataType.inet());
    public static final Converter BIG_DECIMAL = new NativeConverter(DataType.decimal());
    public static final Converter BIG_INTEGER = new NativeConverter(DataType.varint());
    public static final Converter BLOB = new NativeConverter(DataType.blob());

    private final DataType dataType;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    private NativeConverter(DataType dataType) {
        this.dataType = dataType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Converter Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public Object fromCassandraValue(Object value) {
        return value;
    }

    @Override
    public DataType getDataType() {
        return dataType;
    }

    @Override
    public Object toCassandraValue(Object value) {
        return value;
    }
}