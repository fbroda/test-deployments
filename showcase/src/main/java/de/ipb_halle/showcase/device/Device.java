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
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author fbroda
 */
public class Device {

    private String id;
    private String manufacturer;
    private String name;
    private Set<Part> parts;
    private User user;

    public Device() {
        parts = new HashSet<> ();
    }

    public Device(DeviceEntity entity, User u) {
        parts = new HashSet<> ();
        id = entity.getId();
        manufacturer = entity.getManufacturer();
        name = entity.getName();
        user = u;
    }

    public DeviceEntity createEntity() {
        DeviceEntity entity = new DeviceEntity();
        entity.setId(id);
        entity.setManufacturer(manufacturer);
        entity.setName(name);
        entity.setUserId(user.getId());
        return entity;
    }

    public void addPart(Part p) {
        parts.add(p);
    }

    public void addParts(Collection<Part> p) {
        parts.addAll(p);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
