/*
 * Copyright 2013 EnergyOS.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.energyos.espi.datacustodian.domain;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.energyos.espi.datacustodian.support.TestUtils.assertAnnotationPresent;

public class UsagePointTests {

    @Test
    public void isValid() throws Exception {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        UsagePoint usagePoint = new UsagePoint();
        usagePoint.setMRID("urn:uuid:E8E75691-7F9D-49F3-8BE2-3A74EBF6BFC0");
        usagePoint.setServiceCategory(new ServiceCategory(ServiceCategory.ELECTRICITY_SERVICE));

        Set<ConstraintViolation<UsagePoint>> violations = validator.validate(usagePoint);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void isInvalid() throws Exception {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        UsagePoint usagePoint = new UsagePoint();

        Set<ConstraintViolation<UsagePoint>> violations = validator.validate(usagePoint);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void validations() {
        assertAnnotationPresent(UsagePoint.class, "serviceCategory", NotNull.class);
    }

    @Test
    public void meterReadings_hasTransientAnnotation() {
        assertAnnotationPresent(UsagePoint.class, "meterReadings", XmlTransient.class);
    }

    @Test
    public void retailCustomer_hasTransientAnnotation() {
        assertAnnotationPresent(UsagePoint.class, "retailCustomer", XmlTransient.class);
    }
}
