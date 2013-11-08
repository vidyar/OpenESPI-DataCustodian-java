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

package org.energyos.espi.datacustodian.service.impl;


import org.energyos.espi.datacustodian.BaseTest;
import org.energyos.espi.datacustodian.repositories.IntervalBlockRepository;
import org.energyos.espi.datacustodian.service.IntervalBlockService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class IntervalBlockServiceImplTests extends BaseTest {

    private IntervalBlockService service;
    @Mock
    private IntervalBlockRepository repository;

    @Before
    public void setUp() throws Exception {
        service = new IntervalBlockServiceImpl();
        service.setRepository(repository);
    }

    @Test
    public void findAllByMeterReading_returnsIntervalBlocks() {
        service.findAllByMeterReadingId(1L);

        verify(repository).findAllByMeterReadingId(1L);
    }

    @Test
    public void findByHashedId() {
        service.findByHashedId("foo-bar-id");

        verify(repository).findAllByMeterReadingId(1L);
    }
}
