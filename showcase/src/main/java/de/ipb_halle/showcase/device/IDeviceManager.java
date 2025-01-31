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

/**
 *
 * @author fblocal
 */
public interface IDeviceManager {

    /**
     * Create a Device object for the given owner name. Depending on, whether
     * the implementation is a "real" or a mock implementation, the Device
     * may be persisted to the database or not.
     *
     * @param owner a person name
     * @return a Device object.
     */
    public Device manageDevice(String owner);
}
