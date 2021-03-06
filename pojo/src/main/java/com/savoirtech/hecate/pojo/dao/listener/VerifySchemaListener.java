/*
 * Copyright (c) 2012-2016 Savoir Technologies, Inc.
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

package com.savoirtech.hecate.pojo.dao.listener;

import com.datastax.driver.core.Session;
import com.savoirtech.hecate.pojo.dao.PojoDaoFactoryEvent;
import com.savoirtech.hecate.pojo.dao.PojoDaoFactoryListener;

public class VerifySchemaListener implements PojoDaoFactoryListener {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Session session;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public VerifySchemaListener(Session session) {
        this.session = session;
    }

//----------------------------------------------------------------------------------------------------------------------
// PojoDaoFactoryListener Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public <P> void pojoDaoCreated(PojoDaoFactoryEvent<P> event) {
        event.getPojoBinding().verifySchema(session.getCluster().getMetadata().getKeyspace(session.getLoggedKeyspace()), event.getTableName());
    }
}
