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
 */
package de.ipb_halle.test_deployments;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation declares that an injected bean needs to be made
 * available in a test deployment during JUnit tests. Providing a
 * value for the mock property allows to specify a mock bean instead
 * of the original bean.
 *
 * @author fblocal
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD})
public @interface DeploymentElement {

    String mock() default "";

}
