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
package de.ipb_halle.showcase.user;

/**
 *
 * @author fbroda
 */
public class User {

    private String id;
    private String name;

    public User() {
    }

    public User(UserEntity entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public UserEntity createEntity() {
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setName(name);
        return entity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
