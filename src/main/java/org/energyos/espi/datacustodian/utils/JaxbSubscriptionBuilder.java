package org.energyos.espi.datacustodian.utils;

import org.energyos.espi.datacustodian.domain.UsagePoint;
import org.energyos.espi.datacustodian.models.atom.FeedType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 11/4/13
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class JaxbSubscriptionBuilder {
    public FeedType build(List<UsagePoint> usagePoints) {
        FeedBuilder feedBuilder = new FeedBuilder();
        FeedType feedType = feedBuilder.build(usagePoints);

        return feedType;
    }
}
