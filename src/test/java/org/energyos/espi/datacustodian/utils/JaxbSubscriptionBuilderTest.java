package org.energyos.espi.datacustodian.utils;

import org.energyos.espi.datacustodian.domain.UsagePoint;
import org.energyos.espi.datacustodian.models.atom.EntryType;
import org.energyos.espi.datacustodian.models.atom.FeedType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.energyos.espi.datacustodian.utils.factories.EspiFactory.newUsagePointWithId;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 11/4/13
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class JaxbSubscriptionBuilderTest {

    private FeedType feed;
    private EntryType entryType;
    private JaxbSubscriptionBuilder jaxbSubscriptionBuilder;

    @Test
    public void includesUsagePoints() {
        List<UsagePoint> usagePoints = new ArrayList<>();
        usagePoints.add(newUsagePointWithId());

        jaxbSubscriptionBuilder = new JaxbSubscriptionBuilder();

        feed = jaxbSubscriptionBuilder.build(usagePoints);

        assertThat(feed.getEntries().get(0).getContent().getUsagePoint(), is(usagePoints.get(0)));

//        assertHasEntry(feed, UsagePoint.class);
    }

//    @Test
//    public void includesUsagePoint() throws Exception {
//        assertThat(originalUsagePoint, is(feed.getEntries().get(0).getContent().getUsagePoint()));
//    }

//    @Test
//    public void includesMeterReadingEntry() throws FeedException {
//        assertHasEntry(feed, MeterReadingEntry.class);
//    }

    private void assertHasEntry(FeedType feed, Class expected) {
        assertTrue(hasEntry(feed, expected));
    }

    private boolean hasEntry(FeedType feed, Class expected) {
        for (EntryType entry : feed.getEntries()) {
            for (Object content : entry.getContent().getContent()) {
                if (content.getClass().equals(expected)) {
                    return true;
                }
            }
        }
        return false;
    }


}
