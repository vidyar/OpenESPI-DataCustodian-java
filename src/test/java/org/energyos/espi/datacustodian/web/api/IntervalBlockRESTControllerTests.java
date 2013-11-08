package org.energyos.espi.datacustodian.web.api;

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

import com.sun.syndication.io.FeedException;
import org.energyos.espi.datacustodian.domain.IntervalBlock;
import org.energyos.espi.datacustodian.domain.MeterReading;
import org.energyos.espi.datacustodian.domain.UsagePoint;
import org.energyos.espi.datacustodian.service.IntervalBlockService;
import org.energyos.espi.datacustodian.service.RetailCustomerService;
import org.energyos.espi.datacustodian.utils.factories.EspiFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

public class IntervalBlockRESTControllerTests {
    private MockHttpServletResponse response;
    @Mock
    private IntervalBlockService intervalBlockService;
    @Mock
    private RetailCustomerService retailCustomerService;
    @Mock
    private AtomService atomService;
    private IntervalBlockRESTController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        response = new MockHttpServletResponse();

        controller = new IntervalBlockRESTController();
        controller.setIntervalBlockService(intervalBlockService);
        controller.setAtomService(atomService);
    }

    @Test
    public void show() throws IOException, FeedException {
        String entry = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entry></entry>";

        UsagePoint usagePoint = EspiFactory.newUsagePoint();

        MeterReading meterReading = usagePoint.getMeterReadings().get(0);
        IntervalBlock intervalBlock = meterReading.getIntervalBlocks().get(0);
        when(intervalBlockService.findByHashedId(intervalBlock.getHashedId())).thenReturn(intervalBlock);
        when(atomService.entryFor(intervalBlock)).thenReturn(entry);

        controller.show(response, intervalBlock.getHashedId());

        assertThat(response.getContentAsString(), is(entry));
        assertThat(response.getStatus(), is(200));
    }
}
