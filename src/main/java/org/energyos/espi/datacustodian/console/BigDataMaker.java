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

package org.energyos.espi.datacustodian.console;

import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.energyos.espi.datacustodian.domain.*;
import org.energyos.espi.datacustodian.utils.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class BigDataMaker {

//    public static void upload(String filename, String url, HttpClient client) throws IOException {
//        HttpPost post = new HttpPost(url);
//        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//
//        File file = new File(filename);
//        entity.addPart("file", new FileBody(((File) file), "application/rss+xml"));
//
//        post.setEntity(entity);
//
//        client.execute(post);
//    }

    private static long idCounter = 0L;

    private static long now() {
        return System.currentTimeMillis()/1000L;
    }

    public static void main(String[] args) throws FeedException, IOException {
        int numUsagePoints = 0;

        if (args.length == 1) {
            numUsagePoints = Integer.parseInt(args[0]);
            System.out.println("\n\n\nBuilding " + numUsagePoints + " UsagePoints...\n" + now() + "\n\n");
        }
        else {
            System.out.println("YOU BWOKE IT!");
            System.exit(-1);
        }

        File file = new File("BigData.xml");

        FeedBuilder feedBuilder = new FeedBuilder();

        // TODO: Change feedBuilder to take an existing feed and append entries so that we don't have to instantiate
        // all the usage points up front which kills the GC.
        List<UsagePoint> usagePoints = new ArrayList<>();
        for (int i = 0; i < numUsagePoints; i++ ) {
            usagePoints.add(newUsagePoint());
        }

        System.out.println("\n\nBuilt " + idCounter + " objects.\n" + now() + "\n\nBuilding feed...\n\n");

        Feed feed = feedBuilder.buildFeed(usagePoints);

        System.out.println("Built feed.\n" + now() + "\n\nMarshalling feed...\n\n");

        ATOMMarshaller marshaller = new ATOMMarshaller();
        new WireFeedOutput().output(feed, file);

//        System.out.println("Marshalled feed.\n" + now() + "\n\nWriting feed...\n\n");
////        System.out.println(feedXML);
//
//        FileWriter fw = new FileWriter(file.getAbsoluteFile());
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(feedXML);
//        bw.close();



//        if (args.length == 2) {
//            try {
//                String filename = args[0];
//                String url = args[1];
//
//                DefaultHttpClient client = new DefaultHttpClient();
//                client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//                upload(filename, url, client);
//                client.getConnectionManager().shutdown();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Usage: import_usage_point.sh filename url");
//            System.out.println("");
//            System.out.println("Example:");
//            System.out.println("");
//            System.out.println("  import_usage_point.sh etc/usage_point.xml http://localhost:8080/custodian/retailcustomers/1/upload");
//        }
    }

    public static UsagePoint newUsagePoint() {
        return _usagePoint();
    }

    public static MeterReading newMeterReading() {
        return _usagePoint().getMeterReadings().get(0);
    }

    public static ReadingType newReadingType() {
        return _readingType();
    }

    public static ElectricPowerUsageSummary newElectricPowerUsageSummary() {
        return _usagePoint().getElectricPowerUsageSummaries().get(0);
    }

    public static IntervalBlock newIntervalBlock() {
        return _usagePoint().getMeterReadings().get(0).getIntervalBlocks().get(0);
    }

    private static UsagePoint _usagePoint() {
        UsagePoint usagePoint = new UsagePoint();

//        usagePoint.setId(99L);
        usagePoint.setId(idCounter++);
        usagePoint.setMRID("7BC41774-7190-4864-841C-861AC76D46C2");
        usagePoint.setDescription("Electric meter");

        usagePoint.setRoleFlags("role flags".getBytes());
        usagePoint.setStatus(new Short("5"));

        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setKind(new Long(0L));

        usagePoint.setServiceCategory(serviceCategory);

        usagePoint.setRetailCustomer(_retailCustomer());

        // MULTIPLE METER READINGS
        usagePoint.addMeterReading(_meterReading());
        usagePoint.addElectricPowerUsageSummary(_electricPowerUsageSummary());

        return usagePoint;
    }

    private static RetailCustomer _retailCustomer() {
        RetailCustomer retailCustomer = new RetailCustomer();
        retailCustomer.setId(idCounter++);

        return retailCustomer;
    }

    private static MeterReading _meterReading() {
        MeterReading meterReading = new MeterReading();

        meterReading.setId(idCounter++);
        meterReading.setMRID("E8B19EF0-6833-41CE-A28B-A5E7F9F193AE");
        meterReading.setDescription("Electricity consumption");

        for (int i=0; i < 100; i++) {
            meterReading.addIntervalBlock(_intervalBlock());
        }
        meterReading.setReadingType(_readingType());

        return meterReading;
    }

    private static ReadingType _readingType() {
        ReadingType readingType = new ReadingType();

        readingType.setId(idCounter++);
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
        readingType.setFlowDirection("flowDirection");
        readingType.setIntervalLength(10L);
        readingType.setKind("kind");
        readingType.setPhase("phase");
        readingType.setPowerOfTenMultiplier("multiplier");
        readingType.setTimeAttribute("timeAttribute");
        readingType.setUom("uom");
        readingType.setConsumptionTier("consumptionTier");
        readingType.setCpp("cpp");
        readingType.setCurrency("currency");
        readingType.setTou("tou");
        readingType.setAggregate("aggregate");
        readingType.setArgument(argument);
        readingType.setInterharmonic(interharmonic);
        readingType.setMeasuringPeriod("measuringPeriod");

        return readingType;
    }

    private static IntervalBlock _intervalBlock() {
        IntervalBlock intervalBlock = new IntervalBlock();

        DateTimeInterval interval = new DateTimeInterval();
        interval.setDuration(new Long(86400));
        interval.setStart(new Long(1330578000));

        intervalBlock.setId(idCounter++);
        intervalBlock.setMRID("E8E75691-7F9D-49F3-8BE2-3A74EBF6BFC0");
        intervalBlock.setInterval(interval);

        return intervalBlock;
    }

    private static ElectricPowerUsageSummary _electricPowerUsageSummary() {
        ElectricPowerUsageSummary summary = new ElectricPowerUsageSummary();

        summary.setId(idCounter++);
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
