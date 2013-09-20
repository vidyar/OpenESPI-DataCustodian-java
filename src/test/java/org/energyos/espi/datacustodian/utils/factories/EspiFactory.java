package org.energyos.espi.datacustodian.utils.factories;

import org.energyos.espi.datacustodian.domain.*;

import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.UUID;

public class EspiFactory {

    public static UsagePoint newUsagePointOnly(UUID uuid) {
        UsagePoint usagePoint = new UsagePoint();

        usagePoint.setUUID(uuid);
        usagePoint.setDescription("Electric meter");

        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setKind(new Long(0L));

        usagePoint.setServiceCategory(serviceCategory);

        return usagePoint;
    }

    public static UsagePoint newUsagePoint() {
       return newUsagePoint(newRetailCustomer());
    }

    public static UsagePoint newUsagePoint(RetailCustomer retailCustomer) {
        UsagePoint usagePoint = new UsagePoint();

        usagePoint.setMRID("urn:uuid:" + UUID.randomUUID());
        usagePoint.setDescription("Electric meter");

        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setKind(new Long(0L));

        usagePoint.setServiceCategory(serviceCategory);

        usagePoint.setRetailCustomer(retailCustomer);
        usagePoint.addMeterReading(newMeterReading());
        usagePoint.addElectricPowerUsageSummary(newElectricPowerUsageSummary());

        return usagePoint;
    }

    public static MeterReading newMeterReadingWithUsagePoint() {
        return newUsagePoint().getMeterReadings().get(0);
    }

    public static ElectricPowerUsageSummary newElectricPowerUsageSummaryWithUsagePoint() {
        return newUsagePoint().getElectricPowerUsageSummaries().get(0);
    }

    public static IntervalBlock newIntervalBlockWithUsagePoint() {
        return newUsagePoint().getMeterReadings().get(0).getIntervalBlocks().get(0);
    }

    public static RetailCustomer newRetailCustomer() {
        RetailCustomer retailCustomer = new RetailCustomer();
        retailCustomer.setFirstName("First" + System.currentTimeMillis());
        retailCustomer.setLastName("Last" + System.currentTimeMillis());

        return retailCustomer;
    }

    private static MeterReading newMeterReading() {
        MeterReading meterReading = new MeterReading();

        meterReading.setMRID("E8B19EF0-6833-41CE-A28B-A5E7F9F193AE");
        meterReading.setDescription("Electricity consumption");

        meterReading.addIntervalBlock(newIntervalBlock());
        meterReading.addIntervalBlock(newIntervalBlock());
        meterReading.addIntervalBlock(newIntervalBlock());

        meterReading.setReadingType(newReadingType());

        return meterReading;
    }

    public static ReadingType newReadingType() {
        ReadingType readingType = new ReadingType();

        readingType.setDescription("Energy Delivered (kWh)");
        readingType.setMRID("82B3E74B-DFC0-4DD4-8651-91A67B40374D");

        RationalNumber argument = new RationalNumber();
        argument.setNumerator(new BigInteger("1"));
        argument.setDenominator(new BigInteger("3"));

        ReadingInterharmonic interharmonic = new ReadingInterharmonic();
        interharmonic.setNumerator(new BigInteger("1"));
        interharmonic.setDenominator(new BigInteger("6"));

        readingType.setAccumulationBehaviour("accumulationBehaviour");
        readingType.setCommodity("commodity");
        readingType.setDataQualifier("dataQualifier");
        readingType.setIntervalLength(10L);
        readingType.setKind("kind");
        readingType.setPhase("phase");
        readingType.setPowerOfTenMultiplier("multiplier");
        readingType.setUom("uom");
        readingType.setCurrency("currency");
        readingType.setTou("tou");
        readingType.setAggregate("aggregate");
        readingType.setArgument(argument);
        readingType.setInterharmonic(interharmonic);

        return readingType;
    }

    private static IntervalBlock newIntervalBlock() {
        IntervalBlock intervalBlock = new IntervalBlock();

        DateTimeInterval interval = new DateTimeInterval();
        interval.setDuration(new Long(86400));
        interval.setStart(new Long(1330578000));

        intervalBlock.setMRID("E8E75691-7F9D-49F3-8BE2-3A74EBF6BFC0");
        intervalBlock.setInterval(interval);

        return intervalBlock;
    }

    private static ElectricPowerUsageSummary newElectricPowerUsageSummary() {
        ElectricPowerUsageSummary summary = new ElectricPowerUsageSummary();

        summary.setMRID("DEB0A337-B1B5-4658-99CA-4688E253A99B");
        summary.setDescription("Usage Summary");
        summary.setBillingPeriod(new DateTimeInterval(1119600L, 1119600L));
        summary.setCreated(new GregorianCalendar(2012, 10, 24, 0, 0, 0).getTime());
        summary.setUpdated(new GregorianCalendar(2012, 10, 24, 0, 0, 0).getTime());
        summary.setBillLastPeriod(15303000L);
        summary.setBillToDate(1135000L);
        summary.setCostAdditionalLastPeriod(1346000L);
        summary.setCurrency("840");

        SummaryMeasurement summaryMeasurement = new SummaryMeasurement("0", 1331784000L, "72", 93018L);

        summary.setCurrentBillingPeriodOverAllConsumption(summaryMeasurement);
        summary.setQualityOfReading("14");
        summary.setStatusTimeStamp(1331784000L);
        summary.setCurrentDayLastYearNetConsumption(summaryMeasurement);
        summary.setCurrentDayNetConsumption(summaryMeasurement);
        summary.setCurrentDayOverallConsumption(summaryMeasurement);
        summary.setPeakDemand(summaryMeasurement);
        summary.setPreviousDayLastYearOverallConsumption(summaryMeasurement);
        summary.setPreviousDayNetConsumption(summaryMeasurement);
        summary.setPreviousDayOverallConsumption(summaryMeasurement);
        summary.setRatchetDemand(summaryMeasurement);
        summary.setRatchetDemandPeriod(new DateTimeInterval(1119600L, 1119600L));

        return summary;
    }
}
