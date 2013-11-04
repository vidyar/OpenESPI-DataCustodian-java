package org.energyos.espi.datacustodian.domain;

import org.custommonkey.xmlunit.exceptions.XpathException;
import org.energyos.espi.datacustodian.atom.XMLTest;
import org.energyos.espi.datacustodian.models.atom.adapters.IntervalBlockAdapter;
import org.energyos.espi.datacustodian.utils.XMLMarshaller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.IOException;

import static org.custommonkey.xmlunit.XMLAssert.assertXpathExists;
import static org.energyos.espi.datacustodian.support.Asserts.assertXpathValue;
import static org.energyos.espi.datacustodian.support.TestUtils.assertAnnotationPresent;
import static org.energyos.espi.datacustodian.utils.factories.EspiFactory.newIntervalBlockWithUsagePoint;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/test-context.xml")
public class IntervalBlockTests extends XMLTest {
    static final String XML_INPUT =
            "<espi:IntervalBlock xmlns:espi=\"http://naesb.org/espi\">" +
                "<espi:interval>" +
                    "<espi:duration>3</espi:duration>" +
                    "<espi:start>4</espi:start>" +
                "</espi:interval>" +
                "<espi:IntervalReading></espi:IntervalReading>" +
                "<espi:IntervalReading></espi:IntervalReading>" +
            "</espi:IntervalBlock>";
    @Autowired
    XMLMarshaller xmlMarshaller;
    private IntervalBlock intervalBlock;
    private  String xml;

    @Before
    public void before() throws Exception {
        xml = xmlMarshaller.marshal(newIntervalBlockWithUsagePoint());

        intervalBlock = xmlMarshaller.unmarshal(XML_INPUT, IntervalBlock.class);
        callAdapterOnRootElement();
    }

    private void callAdapterOnRootElement() throws Exception {
        // JAXB doesn't seem to call XMLTypeAdapters for the root element.
        // Therefore, we're calling it manually.
        // The best resource we could find: https://java.net/jira/browse/JAXB-117
        // IZ/BK 11/04/2013
        IntervalBlockAdapter intervalBlockAdapter = new IntervalBlockAdapter();
        JAXBElement<IntervalBlock> jaxbElement = new ObjectFactory().createIntervalBlock(intervalBlock);
        intervalBlock = intervalBlockAdapter.unmarshal(jaxbElement);
    }

    @Test
    public void unmarshallsIntervalBlock() {
        assertEquals(IntervalBlock.class, intervalBlock.getClass());
    }

    @Test
    public void unmarshal_setsInterval() {
        assertEquals(3L, intervalBlock.getInterval().getDuration().longValue());
        assertEquals(4L, intervalBlock.getInterval().getStart().longValue());
    }

    @Test
    public void unmarshal_setsIntervalReadings() {
        assertEquals(intervalBlock, intervalBlock.getIntervalReadings().get(0).getIntervalBlock());
        assertEquals(intervalBlock, intervalBlock.getIntervalReadings().get(1).getIntervalBlock());
    }

    @Test
    public void marshall_setsIntervalDuration() throws SAXException, IOException, XpathException {
        assertXpathValue("86400", "espi:IntervalBlock/espi:interval/espi:duration", xml);
    }

    @Test
    public void marshall_setsIntervalStart() throws SAXException, IOException, XpathException {
        assertXpathValue("1330578000", "espi:IntervalBlock/espi:interval/espi:start", xml);
    }

    @Test
    public void marshall_setsIntervalReadings() throws SAXException, IOException, XpathException {
        assertXpathExists("espi:IntervalBlock/espi:IntervalReading[1]", xml);
        assertXpathExists("espi:IntervalBlock/espi:IntervalReading[2]", xml);
    }

    @Test
    public void meterReading_hasTransientAnnotation() {
        assertAnnotationPresent(IntervalBlock.class, "meterReading", XmlTransient.class);
    }
}
