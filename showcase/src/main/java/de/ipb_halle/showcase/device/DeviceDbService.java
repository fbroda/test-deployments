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
import de.ipb_halle.showcase.user.UserDbService;
import de.ipb_halle.test_deployments.PersistenceElements;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fbroda
 */
@Stateless
@PersistenceElements(entities = {DeviceEntity.class, PartEntity.class})
public class DeviceDbService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserDbService userDbService;

    public Device loadDeviceById(String id) {
        DeviceEntity entity = this.em.find(DeviceEntity.class, id);
        User user = userDbService.loadUserById(entity.getUserId());
        Device dev = new Device(entity, user);
        dev.addParts(loadParts(dev));
        return dev;
    }

    private List<Part> loadParts(Device dev) {
        List<Part> result = new ArrayList<> ();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<PartEntity> criteriaQuery = builder.createQuery(PartEntity.class);
        Root<PartEntity> root = criteriaQuery.from(PartEntity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root
                .get(PartEntity.DEVICE_ID),
                dev.getId()));

        for (PartEntity entity: em.createQuery(criteriaQuery).getResultList()) {
            result.add(new Part(entity, dev));
        }
        return result;
    }

    public void save(Device dev) {
        DeviceEntity entity = dev.createEntity();
        this.em.merge(entity);
        saveParts(dev);
    }

    private void saveParts(Device dev) {
        for (Part p : dev.getParts()) {
            this.em.merge(p.createEntity());
        }
    }
}
