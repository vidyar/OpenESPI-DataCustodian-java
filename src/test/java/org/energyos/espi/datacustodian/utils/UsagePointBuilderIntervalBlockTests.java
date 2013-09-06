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

package org.energyos.espi.datacustodian.utils;

import org.energyos.espi.datacustodian.domain.IntervalBlock;
import org.energyos.espi.datacustodian.domain.MeterReading;
import org.energyos.espi.datacustodian.domain.UsagePoint;
import org.energyos.espi.datacustodian.models.atom.ContentType;
import org.energyos.espi.datacustodian.models.atom.EntryType;
import org.energyos.espi.datacustodian.models.atom.FeedType;
import org.energyos.espi.datacustodian.models.atom.LinkType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/spring/test-context.xml")
public class UsagePointBuilderIntervalBlockTests {

    private LinkType newLink(String rel, String href) {
        LinkType link = new LinkType();
        link.setHref(href);
        link.setRel(rel);
        return link;
    }

    @Test
    public void meterReading_hasInervalBlocks() {
//
//        FeedType feedType = new FeedType();
//
//        EntryType usagePointEntry = new EntryType();
//        EntryType meterReadingEntry = new EntryType();
//        EntryType intervalBlockEntry = new EntryType();
//
//        usagePointEntry.getLinks().add(newLink("self", "RetailCustomer/9b6c7063/UsagePoint/01"));
//        usagePointEntry.getLinks().add(newLink("related", "RetailCustomer/9b6c7063/UsagePoint/01/MeterReading"));
//        usagePointEntry.setContent(new ContentType());
//        usagePointEntry.getContent().setUsagePoint(new UsagePoint());
//
//        meterReadingEntry.getLinks().add(newLink("self", "RetailCustomer/9b6c7063/UsagePoint/01/MeterReading/01"));
//        meterReadingEntry.getLinks().add(newLink("up", "RetailCustomer/9b6c7063/UsagePoint/01/MeterReading"));
//        meterReadingEntry.getLinks().add(newLink("related", "RetailCustomer/9b6c7063/UsagePoint/01/MeterReading/01/IntervalBlock"));
//        meterReadingEntry.setContent(new ContentType());
//        meterReadingEntry.getContent().setMeterReading(new MeterReading());
//
//        intervalBlockEntry.getLinks().add(newLink("self", "RetailCustomer/9b6c7063/UsagePoint/01/MeterReading/01/IntervalBlock/0173"));
//        intervalBlockEntry.getLinks().add(newLink("up", "RetailCustomer/9b6c7063/UsagePoint/01/MeterReading/01/IntervalBlock"));
//        IntervalBlock intervalBlock1 = new IntervalBlock();
//        IntervalBlock intervalBlock2 = new IntervalBlock();
//        ContentType intervalBlockContent = new ContentType();
//        List<IntervalBlock> intervalBlocks = new ArrayList<>();
//        intervalBlocks.add(intervalBlock1);
//        intervalBlocks.add(intervalBlock2);
//        intervalBlockContent.setIntervalBlocks(intervalBlocks);
//        intervalBlockEntry.setContent(intervalBlockContent);
//
//        feedType.getEntries().add(usagePointEntry);
//        feedType.getEntries().add(meterReadingEntry);
//        feedType.getEntries().add(intervalBlockEntry);
//
//        UsagePointBuilder builder = new UsagePointBuilder();
//        UsagePoint usagePoint = builder.newUsagePoint(feedType);
//        MeterReading meterReading = usagePoint.getMeterReadings().get(0);
//
//        assertTrue(meterReading.getIntervalBlocks().contains(intervalBlock1));
//        assertTrue(meterReading.getIntervalBlocks().contains(intervalBlock2));
//        assertEquals(2, meterReading.getIntervalBlocks().size());
    }
}

