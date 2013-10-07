package org.energyos.espi.datacustodian.atom;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.io.FeedException;
import org.energyos.espi.datacustodian.domain.IntervalBlock;
import org.energyos.espi.datacustodian.domain.MeterReading;
import org.energyos.espi.datacustodian.domain.UsagePoint;
import org.energyos.espi.datacustodian.models.atom.ContentType;
import org.energyos.espi.datacustodian.utils.EspiMarshaller;

import java.util.ArrayList;
import java.util.List;

public class IntervalBlocksEntry extends EspiEntry<IntervalBlock> {


    public IntervalBlocksEntry(List<IntervalBlock> intervalBlocks) throws FeedException {
        super(intervalBlocks.get(0));

        this.setContent(buildContents(intervalBlocks));
    }

    private ContentType buildContents(List<IntervalBlock> intervalBlocks) throws FeedException {
        ContentType content = new ContentType();

        content.setEntity(intervalBlocks);

        return content;
    }

    protected String getSelfHref() {
        MeterReading meterReading = espiObject.getMeterReading();
        UsagePoint usagePoint = meterReading.getUsagePoint();

        return "RetailCustomer/" + usagePoint.getRetailCustomer().getId() +
                "/UsagePoint/" + usagePoint.getId() +
                "/MeterReading/" + meterReading.getId() +
                "/IntervalBlock/" + espiObject.getId();
    }

    protected String getUpHref() {
        MeterReading meterReading = espiObject.getMeterReading();
        UsagePoint usagePoint = meterReading.getUsagePoint();

        return "RetailCustomer/" + usagePoint.getRetailCustomer().getId() +
                "/UsagePoint/" + usagePoint.getId() +
                "/MeterReading/" + meterReading.getId() +
                "/IntervalBlock";
    }

    protected void buildRelatedLinks() {}
}
