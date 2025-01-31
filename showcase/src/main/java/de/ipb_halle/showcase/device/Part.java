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

import de.ipb_halle.showcase.user.*;
import java.util.UUID;

/**
 *
 * @author fbroda
 */
public class Part {

    private String id;
    private Integer count;
    private String name;
    private Device device;

    public Part() {
    }

    public Part(Device d, Integer c, String n) {
        id = UUID.randomUUID().toString();
        count = c;
        device = d;
        name = n;
    }

    public Part(PartEntity entity, Device d) {
        id = entity.getId();
        count = entity.getCount();
        name = entity.getName();
        device = d;
    }

    public PartEntity createEntity() {
        PartEntity entity = new PartEntity();
        entity.setId(id);
        entity.setCount(count);
        entity.setName(name);
        entity.setDeviceId(device.getId());
        return entity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
