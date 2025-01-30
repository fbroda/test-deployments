# Test Deployments
This project experiments with a simplification for complex Jakarta-EE JUnit5 test scenarios. Some tests have extensive dependencies on beans and JPA entities. For the JUnit test, all (recursively) used dependencies must be listed in the test class, otherwise CDI cannot resolve the dependencies at test runtime. The <code>@Classes</code> annotation is available for this purpose. The list of packages to be listed may become very long and confusing. I am looking for an option to automate the list generation or to compose it.

Additionally, this project deals with an annotation to automatically configure the JPA entities used by a test.

The project is divided into three parts
* A showcase Apache OpenEJB project to demonstrate the use of the annotations
* An annotation processor to be used during test compilation (so far only tested with Maven)
* The annotations themselves. One (<code>@DeploymentElement</code>) to be used in the test code to mark beans for deployment in the test environment, the other (<code>@PersistenceElements</code>) to inform about JPA entities, which are serviced by a specific bean.
