/*
 * Test Deployments
 * Copyright 2025 Leibniz-Institut f. Pflanzenbiochemie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.ipb_halle.showcase;

import java.util.Arrays;
import java.util.Properties;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;

/**
 * Base or support class for frequently occurring tasks in unit tests
 *
 * @author fblocal
 */
public class TestBase {

    /**
     * provide the EJB configuration
     * @return a properties object
     */
    public static Properties configuration() {
        Properties properties = new Properties();
        properties.put("openejb.configuration", TestBase.class.getResource("/test-openejb.xml").getFile());
        return properties;
    }

    /**
     * create a PersistenceUnit using the provided entities
     * @param entities array of JPA entity classes to be handled by the PersistenceUnit
     * @return configured PersistenceUnit
     */
   public static PersistenceUnit persistence(String [] entities) {
        PersistenceUnit unit = new PersistenceUnit("signalsDB");
        unit.setJtaDataSource("testDS");
        unit.setNonJtaDataSource("testDSNonJTA");
        unit.setProvider("org.hibernate.jpa.HibernatePersistenceProvider");
        unit.getClazz().addAll(Arrays.asList(entities));
        unit.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
//      unit.setProperty("hibernate.show_sql", "true");
//      unit.setProperty("hibernate.format_sql", "true");
//      unit.setProperty("hibernate.use_sql_comments", "true");
        unit.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
        unit.setProperty("jakarta.persistence.schema-generation.database.action", "create-drop");
        unit.setProperty("jakarta.persistence.schema-generation.create-script-source", "schema.sql");
        unit.setProperty("jakarta.persistence.schema-generation.drop-script-source", "drop_schema.sql");
        unit.setProperty("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.schema.internal.script.MultiLineSqlScriptExtractor");

        unit.setProperty("tomee.jpa.cdi", "false");
        return unit;
    }

}
