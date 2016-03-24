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

package com.savoirtech.hecate.pojo.binding.column;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.datastax.driver.core.TableMetadata;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.schemabuilder.Create;
import com.savoirtech.hecate.pojo.binding.FacetBinding;
import com.savoirtech.hecate.pojo.binding.ParameterBinding;
import com.savoirtech.hecate.pojo.binding.PojoVisitor;
import com.savoirtech.hecate.pojo.facet.Facet;
import com.savoirtech.hecate.pojo.query.PojoQueryContext;

public class NestedColumnBinding extends AbstractColumnBinding {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final List<FacetBinding> facetBindings;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public NestedColumnBinding(List<FacetBinding> facetBindings) {
        this.facetBindings = facetBindings;
    }

//----------------------------------------------------------------------------------------------------------------------
// ColumnBinding Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public void collectParameters(Object pojo, List<Object> parameters) {
        forEachBinding(binding -> binding.collectParameters(pojo, parameters));
    }

    @Override
    public void create(Create create) {
        forEachBinding(binding -> binding.create(create));
    }

    @Override
    public List<ParameterBinding> getParameterBindings() {
        return facetBindings.stream().flatMap(binding -> binding.getParameterBindings().stream()).collect(Collectors.toList());
    }

    @Override
    public void injectValues(Object pojo, Iterator<Object> columnValues, PojoQueryContext context) {
        forEachBinding(binding -> binding.injectValues(pojo, columnValues, context));
    }

    @Override
    public void insert(Insert insert) {
        forEachBinding(facetBinding -> facetBinding.insert(insert));
    }

    @Override
    public void select(Select.Selection select) {
        forEachBinding(binding -> binding.select(select));
    }

    @Override
    public void verifySchema(TableMetadata metadata) {
        forEachBinding(binding -> binding.verifySchema(metadata));
    }

    @Override
    public void visitChildren(Object pojo, Predicate<Facet> predicate, PojoVisitor visitor) {
        forEachBinding(binding -> binding.visitChildren(pojo, predicate, visitor));
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public List<FacetBinding> getFacetBindings() {
        return facetBindings;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected void forEachBinding(Consumer<FacetBinding> consumer) {
        facetBindings.stream().forEach(consumer);
    }
}
