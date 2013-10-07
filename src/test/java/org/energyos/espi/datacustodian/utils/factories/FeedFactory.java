package org.energyos.espi.datacustodian.utils.factories;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.sun.syndication.io.FeedException;
import org.energyos.espi.datacustodian.atom.UsagePointEntry;
import org.energyos.espi.datacustodian.models.atom.DateTimeType;
import org.energyos.espi.datacustodian.models.atom.FeedType;
import org.energyos.espi.datacustodian.models.atom.IdType;
import org.energyos.espi.datacustodian.models.atom.LinkType;
import org.joda.time.DateTime;

import org.energyos.espi.datacustodian.utils.factories.EspiFactory;

import java.util.Date;

import static org.energyos.espi.datacustodian.utils.factories.EspiFactory.newUsagePoint;

public class FeedFactory {
    private FeedFactory() {}

    public static FeedType newFeed() throws FeedException {
        FeedType feed = new FeedType();

        IdType feedId = new IdType();
        feedId.setValue("0071C5A7-91CF-434E-8BCE-C38AC8AF215D");
        feed.setId(feedId);
        feed.setTitle("Feed title");
        DateTimeType updated = new DateTimeType();
        XMLGregorianCalendarImpl updateGreg = new XMLGregorianCalendarImpl();
        updateGreg.setYear(113);
        updateGreg.setDay(28);
        updateGreg.setMonth(11);
        updated.setValue(updateGreg);


        feed.setUpdated(updated);

        LinkType self = newLink("self", "/ThirdParty/83e269c1/Batch");
        feed.getLinks().add(self);

        feed.getEspiEntries().add(newUsagePointEntry());
        return feed;
    }

    public static UsagePointEntry newUsagePointEntry() throws FeedException {
        UsagePointEntry entry = new UsagePointEntry(newUsagePoint());

        return entry;
    }

    public static LinkType newLink(String rel, String href) {
        LinkType entrySelf = new LinkType();
        entrySelf.setRel(rel);
        entrySelf.setHref(href);
        return entrySelf;
    }
}
