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

import de.ipb_halle.showcase.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fblocal
 */
public class MockDeviceManager implements IDeviceManager {

    public final static String ID = "__mocked__";

    private Logger logger = LoggerFactory.getLogger(MockDeviceManager.class);

    @Override
    public Device manageDevice(String owner) {
        Device mockDevice = new Device();
        mockDevice.setId(ID);
        mockDevice.addPart(new Part(mockDevice, 1, "Mock Part"));
        User mockUser = new User();
        mockUser.setId(ID);
        mockUser.setName(owner);
        mockDevice.setUser(mockUser);

        logger.info("""

                         ************************************************************
                         *
                         * MockDeviceManager created mock device.
                         *
                         ************************************************************
                         """);

        return mockDevice;
    }
}
