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

import de.ipb_halle.test_deployments.DeploymentElement;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Template for JUnit test using a mock component. The use of the
 * <code>DeploymentElement</code> annotation makes the test much smaller.
 *
 * Tests using the <code>@DeploymentElement</code> annotation need two to four
 * additional compiler options:
 * * <code>-Aclasspath:${maven.compile.classpath}</code> for the packages referenced by the code
 * * <code>-Atestbase:fully.qualified.class.Name</code> name of the TestBase class. Maybe omitted, if not working with JPA entities.
 * * <code>-Abuilddir:${maven.project.outputDirectory}</code> for the path to the <code>target/classes</code> directory. Necessary if using a parent pom. Defaults to <code>target.classes</code>.
 * * <code>-Atestprefix:SomePrefix</code> for the name prefix for the generated concrete test class. Defaults to <code>TDGenerated</code>
 *
 * @author fblocal
 */
public abstract class NewDeviceManagerMockTest {

    private final static String TEST_OWNER = "Johann von der Mocken";


    // this time we want the mock object to be injected
    @Inject
    @DeploymentElement(mock="de.ipb_halle.showcase.device.MockDeviceManager")
    private IDeviceManager deviceManager;

    @Inject
    @DeploymentElement
    private DeviceDbService deviceDbService;

    @Test
    public void testDevice() {
        Device fromManager = deviceManager.manageDevice(TEST_OWNER);
        Device fromDb = deviceDbService.loadDeviceById(fromManager.getId());

        Assertions.assertEquals(MockDeviceManager.ID, fromManager.getId(), "object has mock id");
        Assertions.assertNull(fromDb, "Mock manager does not persist object");
    }
}