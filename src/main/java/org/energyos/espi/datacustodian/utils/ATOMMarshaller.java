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

import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;
import org.apache.commons.lang.StringEscapeUtils;
import org.energyos.espi.datacustodian.models.atom.FeedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.util.JAXBResult;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringWriter;

@Component
public class ATOMMarshaller {

    @Qualifier("jaxb2Marshaller")
    @Autowired
    private Jaxb2Marshaller unmarshaller;
    private static Marshaller marshaller;
    private static JAXBContext jaxbContext;

    public FeedType unmarshal(InputStream stream) throws JAXBException {
        @SuppressWarnings("unchecked")
        JAXBElement<FeedType> object = (JAXBElement<FeedType>) unmarshaller.unmarshal(new StreamSource(stream));
        return object.getValue();
    }

    public String marshal(FeedType feed) throws FeedException {
        final StringWriter out = new StringWriter();
//        marshaller.marshal(feed, new StreamResult(out));
//        return out.toString();

        try {
            getMarshaller().marshal(feed, out);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new FeedException("Invalid " + feed.getClass().toString() + ". Could not serialize.");
        }

        return out.toString();
    }

    private static Marshaller getMarshaller() throws JAXBException {
        if (marshaller == null) {
            JAXBContext jaxbContext = getJaxbContext();
            marshaller = jaxbContext.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        }

        return marshaller;
    }

    private static JAXBContext getJaxbContext() throws JAXBException {
        if (jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance("org.energyos.espi.datacustodian.models.atom");
        }

        return jaxbContext;
    }
}
