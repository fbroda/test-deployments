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
package de.ipb_halle.showcase.device;

import de.ipb_halle.showcase.TestBase;
import de.ipb_halle.showcase.user.UserDbService;
import de.ipb_halle.showcase.user.UserManager;
import de.ipb_halle.showcase.user.UserEntity;
import jakarta.inject.Inject;
import java.util.Properties;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit5.RunWithApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Classical JUnit test for the DeviceManager component. This time, the a
 * MockDeviceManager is used. Again, all used beans and entities have been
 * configured manually, either with the <code>@Classes</code>
 * annotation or by listing them in the <code>persistence()</code> method.
 *
 * A mock component which emulates specific aspects of a component for a
 * test, but otherwise does not care about too much detail.
 *
 * @author fblocal
 */
@RunWithApplicationComposer
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeviceManagerMockTest {

    private final static String TEST_OWNER = "Jonathan van Doe";

    @Inject
    private IDeviceManager deviceManager;

    @Inject
    private DeviceDbService deviceDbService;

    @Module
    @Classes(cdi = true, value = {MockDeviceManager.class,
                DeviceDbService.class,
                UserManager.class,
                UserDbService.class})
    public EjbJar app() {
        return new EjbJar();
    }

    @Module
    public PersistenceUnit persistence() {
        return TestBase.persistence(new String[]{
                    DeviceEntity.class.getName(),
                    PartEntity.class.getName(),
                    UserEntity.class.getName()});
    }

    @Configuration
    public Properties configuration() {
        return TestBase.configuration();
    }


    @Test
    public void testDevice() {
        Device fromManager = deviceManager.manageDevice(TEST_OWNER);
        Device fromDb = deviceDbService.loadDeviceById(fromManager.getId());

        Assertions.assertEquals(fromManager.getId(), MockDeviceManager.ID, "object has mock id");
        Assertions.assertNull(fromDb, "Mock manager does not persist object");
    }
}