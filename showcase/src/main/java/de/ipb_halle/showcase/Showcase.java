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
package de.ipb_halle.showcase;


import de.ipb_halle.showcase.device.DeviceManager;
import de.ipb_halle.showcase.user.UserManager;
import jakarta.ejb.embeddable.EJBContainer;
import jakarta.inject.Inject;
import java.util.Properties;
import javax.naming.Context;
import org.apache.openejb.api.LocalClient;


/**
 * Minimal embedded OpenEJB container for demonstration purposes.
 * @author fbroda
 */
@LocalClient
public class Showcase {

    @Inject
    private DeviceManager deviceManager;

    @Inject
    private UserManager userManager;

    private void manage() {
        // we do not care for the return value
        // testing is the interesting part
        userManager.manageUser("John Doe");
        deviceManager.manageDevice("Jane Doe");
    }

    public static Showcase getInstance() {
        Showcase showcase = null;
        try {

            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            properties.put("openejb.configuration", "jar:" + Showcase.class.getResource("/openejb.xml").getFile());

            EJBContainer container = EJBContainer.createEJBContainer(properties);
            Context ctx = container.getContext();

            showcase = new Showcase();
            ctx.bind("inject", showcase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showcase;
    }

    /**
     * The "production" version of this showcase. Just build the project with
     * <code>mvn install<code> and then run the showcase with
     * <code>java -jar showcase/target/showcase-1.0-jar-with-dependencies.jar</code>
     * without any further arguments.
     *
     * @param argv
     */
    public static void main(String[] argv) {
        Showcase showcase = getInstance();
        showcase.manage();
    }
}
