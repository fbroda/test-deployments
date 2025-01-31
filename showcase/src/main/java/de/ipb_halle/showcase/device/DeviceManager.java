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
import de.ipb_halle.showcase.user.UserManager;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author fbroda
 */
public class DeviceManager {

    private final Logger logger = LoggerFactory.getLogger(DeviceManager.class);

    @Inject
    private DeviceDbService deviceDbService;

    @Inject
    private UserManager userManager;

    private Device createBike(String owner) {
        User user = userManager.manageUser(owner);
        Device bike = new Device();
        bike.setId(UUID.randomUUID().toString());
        bike.setName("ACME Bike");
        bike.setManufacturer("ACME Corp.");
        bike.setUser(user);

        List<Part> parts = new ArrayList<> ();
        parts.add(new Part(bike, 2, "wheel"));
        parts.add(new Part(bike, 1, "frame"));
        parts.add(new Part(bike, 1, "saddle"));
        parts.add(new Part(bike, 2, "pedals"));
        parts.add(new Part(bike, 1, "handlebars"));

        bike.addParts(parts);
        return bike;
    }

    public Device manageDevice(String owner) {
        Device dev = createBike(owner);
        deviceDbService.save(dev);
        this.logger.info("DeviceManager created bike {} with id {} for {}",
                dev.getName(), dev.getId(), dev.getUser().getName());
        return dev;
    }
}
