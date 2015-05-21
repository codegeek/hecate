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

package com.savoirtech.hecate.pojo.facet.property;

import com.savoirtech.hecate.pojo.facet.Facet;
import com.savoirtech.hecate.pojo.facet.FacetProvider;
import org.junit.Assert;
import org.junit.Test;

import java.beans.Transient;
import java.util.List;
import java.util.Map;

public class PropertyFacetProviderTest extends Assert {

    @Test
    public void testGetFacets() throws Exception {
        FacetProvider provider = new PropertyFacetProvider();
        Map<String,Facet> facets = provider.getFacetsAsMap(PropertiesPojo.class);
        assertNotNull(facets);
        assertEquals(1, facets.size());
        assertTrue(facets.containsKey("baz"));
    }

    @Test
    public void testValueHandling() {
        FacetProvider provider = new PropertyFacetProvider();
        List<Facet> facets = provider.getFacets(PropertiesPojo.class);
        PropertiesPojo pojo = new PropertiesPojo();
        Facet facet= facets.get(0);
        assertNull(facet.getValue(pojo));
        facet.setValue(pojo, "testValue");
        assertEquals("testValue", facet.getValue(pojo));
    }

    @Test
    public void testGetType() {
        FacetProvider provider = new PropertyFacetProvider();
        List<Facet> facets = provider.getFacets(PropertiesPojo.class);
        PropertiesPojo pojo = new PropertiesPojo();
        Facet facet= facets.get(0);
        assertEquals(String.class, facet.getType().getRawType());
    }

    @Test
    public void testGetAnnotation() {
        FacetProvider provider = new PropertyFacetProvider();
        Map<String,Facet> map = provider.getFacetsAsMap(PropertiesPojo.class);
        Facet facet = map.get("baz");
        assertNotNull(facet.getAnnotation(Deprecated.class));

    }

    public static class PropertiesPojo {
        private String foo;
        private String bar;
        private String baz;

        private String ignored;

        @Transient
        public String getIgnored() {
            return ignored;
        }

        public void setIgnored(String ignored) {
            this.ignored = ignored;
        }

        public String getFoo() {
            return foo;
        }

        public void setBar(String bar) {
            this.bar = bar;
        }

        @Deprecated
        public String getBaz() {
            return baz;
        }

        public void setBaz(String baz) {
            this.baz = baz;
        }
    }
}