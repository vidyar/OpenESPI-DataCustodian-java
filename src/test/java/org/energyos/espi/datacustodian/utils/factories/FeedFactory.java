package org.energyos.espi.datacustodian.utils.factories;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;
import org.energyos.espi.datacustodian.atom.UsagePointEntry;
import org.energyos.espi.datacustodian.domain.*;
import org.energyos.espi.datacustodian.models.atom.ContentType;
import org.energyos.espi.datacustodian.models.atom.EntryType;
import org.energyos.espi.datacustodian.models.atom.FeedType;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedFactory {
    private FeedFactory() {}

    public static FeedType newFeedType() {
        FeedType feedType = new FeedType();

        feedType.setId("urn:uuid:0071C5A7-91CF-434E-8BCE-C38AC8AF215D");

        feedType.setTitle("Feed title");
        feedType.setUpdated(new Date(113, 11, 28));
        feedType.setSelfLinkHref("/ThirdParty/83e269c1/Batch");
        feedType.getEntries().add(newUsagePointEntryType());
        feedType.getEntries().add(newMeterReadingEntryType());
        feedType.getEntries().add(newReadingTypeEntryType());
        feedType.getEntries().add(newElectricPowerUsageSummaryEntryType());
        feedType.getEntries().add(newElectricPowerQualitySummaryEntryType());
        feedType.getEntries().add(newIntervalBlocksEntryType());
        feedType.getEntries().add(new EntryType());
        return feedType;
    }

    private static EntryType newIntervalBlocksEntryType() {
        EntryType entry = new EntryType();
        entry.setId("urn:uuid:7BC41774-7190-4864-841C-861AC76D46C2");
        entry.setSelfLinkHref("RetailCustomer/9b6c7063/UsagePoint/01/MeterReading/01/IntervalBlock/01");
        entry.setUpLinkHref("RetailCustomer/9b6c7063/UsagePoint/01/MeterReading/01/IntervalBlock");
        entry.setPublished(new DateTime(new Date(112, 10, 15)));
        entry.setUpdated(new DateTime(new Date(112, 10, 17)));
        entry.setTitle(null);
        entry.setContent(newIntervalBlocksContentType());
        return entry;
    }

    private static ContentType newIntervalBlocksContentType() {
        ContentType content = new ContentType();
        List<IntervalBlock> intervalBlocks = new ArrayList<>();
        intervalBlocks.add(new IntervalBlock());
        intervalBlocks.add(new IntervalBlock());

        content.setIntervalBlocks(intervalBlocks);
        return content;
    }

    private static EntryType newElectricPowerQualitySummaryEntryType() {
        EntryType entry = new EntryType();
        entry.setId("urn:uuid:7BC41774-7190-4864-841C-861AC76D46C2");
        entry.setSelfLinkHref("RetailCustomer/9b6c7063/ElectricPowerUsageSummary/01");
        entry.setUpLinkHref("RetailCustomer/9b6c7063/UsagePoint/01/ElectricPowerUsageSummary");
        entry.setPublished(new DateTime(new Date(112, 10, 15)));
        entry.setUpdated(new DateTime(new Date(112, 10, 17)));
        entry.setTitle("Quality Summary");
        entry.setContent(newElectricPowerQualitySummaryContentType());
        return entry;
    }

    private static ContentType newElectricPowerQualitySummaryContentType() {
        ContentType content = new ContentType();
        content.setElectricPowerQualitySummary(new ElectricPowerQualitySummary());
        return content;
    }

    private static EntryType newElectricPowerUsageSummaryEntryType() {
        EntryType entry = new EntryType();
        entry.setId("urn:uuid:7BC41774-7190-4864-841C-861AC76D46C2");
        entry.setSelfLinkHref("RetailCustomer/9b6c7063/ElectricPowerUsageSummary/01");
        entry.setUpLinkHref("RetailCustomer/9b6c7063/UsagePoint/01/ElectricPowerUsageSummary");
        entry.setPublished(new DateTime(new Date(112, 10, 15)));
        entry.setUpdated(new DateTime(new Date(112, 10, 17)));
        entry.setTitle("Usage Summary");
        entry.setContent(newElectricPowerUsageSummaryContentType());
        return entry;
    }

    private static ContentType newElectricPowerUsageSummaryContentType() {
        ContentType content = new ContentType();
        content.setElectricPowerUsageSummary(new ElectricPowerUsageSummary());
        return content;
    }

    private static EntryType newReadingTypeEntryType() {
        EntryType entry = new EntryType();
        entry.setId("urn:uuid:7BC41774-7190-4864-841C-861AC76D46C2");
        entry.setSelfLinkHref("ReadingType/01");
        entry.setUpLinkHref("ReadingType");
        entry.setPublished(new DateTime(new Date(112, 10, 15)));
        entry.setUpdated(new DateTime(new Date(112, 10, 17)));
        entry.setTitle("Energy Delivered (kWh)");
        entry.setContent(newReadingTypeContentType());
        return entry;
    }

    private static ContentType newReadingTypeContentType() {
        ContentType content = new ContentType();
        content.setReadingType(new ReadingType());
        return content;
    }

    private static EntryType newMeterReadingEntryType() {
        EntryType entry = new EntryType();
        entry.setId("urn:uuid:7BC41774-7190-4864-841C-861AC76D46C2");
        entry.setSelfLinkHref("RetailCustomer/9b6c7063/UsagePoint/01/MeterReading/01");
        entry.setUpLinkHref("RetailCustomer/9b6c7063/UsagePoint/01/MeterReading");
        entry.setRelatedLinkHref("ReadingType/01");
        entry.setPublished(new DateTime(new Date(112, 10, 15)));
        entry.setUpdated(new DateTime(new Date(112, 10, 17)));
        entry.setTitle("Fifteen Minute Electricity Consumption");
        entry.setContent(newMeterReadingContentType());
        return entry;
    }

    private static ContentType newMeterReadingContentType() {
        ContentType content = new ContentType();
        content.setMeterReading(new MeterReading());
        return content;
    }

    private static EntryType newUsagePointEntryType() {
        EntryType entry = new EntryType();
        entry.setId("urn:uuid:7BC41774-7190-4864-841C-861AC76D46C2");
        entry.setSelfLinkHref("RetailCustomer/9b6c7063/UsagePoint/01");
        entry.setUpLinkHref("RetailCustomer/9b6c7063/UsagePoint");
        entry.setRelatedLinkHref("RetailCustomer/9b6c7063/UsagePoint/01/MeterReading");
        entry.setPublished(new DateTime(new Date(112, 10, 15)));
        entry.setUpdated(new DateTime(new Date(112, 10, 17)));
//        entry.setTitle("Front Electric Meter");
        entry.setContent(newContentType());
        return entry;
    }

    private static ContentType newContentType() {
        ContentType content = new ContentType();
        UsagePoint usagePoint = new UsagePoint();
        usagePoint.setDescription("foo desc");
        content.setUsagePoint(usagePoint);
        return content;
    }

    public static Feed newFeed() {
        Feed feed = new Feed();
        feed.setFeedType("atom_1.0");
        feed.setId("urn:uuid:0071C5A7-91CF-434E-8BCE-C38AC8AF215D");
        feed.setTitle("Feed title");
        feed.setUpdated(new Date(113, 11, 28));

        Link self = newLink("self", "/ThirdParty/83e269c1/Batch");
        feed.getAlternateLinks().add(self);

        feed.getEntries().add(newUsagePointEntry());
        feed.getEntries().add(new Entry());
        return feed;
    }

    public static Entry newUsagePointEntry() {
        Entry entry = new Entry();
        entry.setId("urn:uuid:7BC41774-7190-4864-841C-861AC76D46C2");
        entry.setTitle("Front Electric Meter");
        entry.setPublished(new Date(112, 10, 15));
        entry.setUpdated(new Date(112, 10, 17));

        entry.getContents().add(newContent("<UsagePoint/>"));

        entry.getAlternateLinks().add(newLink("self", "RetailCustomer/9b6c7063/UsagePoint/01"));
        entry.getAlternateLinks().add(newLink("up", "RetailCustomer/9b6c7063/UsagePoint"));
        entry.getAlternateLinks().add(newLink("related", "RetailCustomer/9b6c7063/UsagePoint/01/MeterReading"));

        return entry;
    }

    public static Link newLink(String rel, String href) {
        Link entrySelf = new Link();
        entrySelf.setRel(rel);
        entrySelf.setHref(href);
        return entrySelf;
    }

    public static Content newContent(String value) {
        Content content = new Content();
        content.setValue(value);

        return content;
    }
}
