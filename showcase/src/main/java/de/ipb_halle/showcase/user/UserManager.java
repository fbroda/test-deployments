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

import jakarta.inject.Inject;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author fbroda
 */
public class UserManager {

    private final Logger logger = LoggerFactory.getLogger(UserManager.class);

    @Inject
    private UserDbService userDbService;

    private User createUser(String name) {
        String id = UUID.randomUUID().toString();
        User user = new User();
        user.setId(id);
        user.setName(name);
        userDbService.save(user);
        return user;
    }

    public User manageUser(String name) {
        User u = createUser(name);
        this.logger.info("UserManager created user {} with id {}", u.getName(), u.getId());
        return u;
    }
}
