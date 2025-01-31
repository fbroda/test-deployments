/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.ipb_halle.showcase.device;

import de.ipb_halle.showcase.TestBase;
import de.ipb_halle.showcase.user.UserDbService;
import de.ipb_halle.showcase.user.UserManager;
import de.ipb_halle.showcase.user.UserEntity;
import jakarta.inject.Inject;
import java.util.Properties;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit5.RunWithApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Classical JUnit test for the DeviceManager component. All used beans
 * and entities have been configured manually, either with the <code>@Classes</code>
 * annotation or by listing them in the <code>persistence()</code> method.
 * @author fblocal
 */
@RunWithApplicationComposer
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeviceManagerTest {

    private final static String TEST_OWNER = "Jonathan van Doe";

    @Inject
    private IDeviceManager deviceManager;

    @Inject
    private DeviceDbService deviceDbService;

    @Module
    @Classes(cdi = true, value = {DeviceManager.class,
                DeviceDbService.class,
                UserManager.class,
                UserDbService.class})
    public EjbJar app() {
        return new EjbJar();
    }

    @Module
    public PersistenceUnit persistence() {
        return TestBase.persistence(new String[]{
                    DeviceEntity.class.getName(),
                    PartEntity.class.getName(),
                    UserEntity.class.getName()});
    }

    @Configuration
    public Properties configuration() {
        return TestBase.configuration();
    }


    @Test
    public void testDevice() {
        Device fromManager = deviceManager.manageDevice(TEST_OWNER);
        Device fromDb = deviceDbService.loadDeviceById(fromManager.getId());

        Assertions.assertEquals(TEST_OWNER, fromDb.getUser().getName(), "owner name mismatch");
    }
}