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

import de.ipb_halle.test_deployments.PersistenceElements;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author fbroda
 */
@Stateless
@PersistenceElements(entities = {UserEntity.class})
public class UserDbService {

    @PersistenceContext
    private EntityManager em;

    public User loadUserById(String id) {
        UserEntity entity = this.em.find(UserEntity.class, id);
        return new User(entity);
    }

    public void save(User u) {
        UserEntity entity = u.createEntity();
        this.em.merge(entity);
    }
}
