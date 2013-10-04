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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2013.08.27 at 01:43:57 PM EDT
//


package org.energyos.espi.datacustodian.models.atom;

import org.energyos.espi.datacustodian.domain.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The Atom content construct is defined in section 4.1.3 of the format spec.
 * <p/>
 * <p/>
 * <p>Java class for contentType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="contentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.w3.org/2005/Atom}commonAttributes"/>
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contentType", propOrder = {
        "usagePoint",
        "meterReading",
        "intervalBlocks",
        "electricPowerUsageSummary",
        "electricPowerQualitySummary",
        "readingType",
        "content"
})
public class ContentType {

    @XmlElementRefs({
            @XmlElementRef(name = "UsagePoint", namespace = "http://naesb.org/espi", type = JAXBElement.class, required = false),
    })
    @XmlAnyElement(lax = true)
    protected UsagePoint usagePoint;

    @XmlElementRefs({
            @XmlElementRef(name = "MeterReading", namespace = "http://naesb.org/espi", type = JAXBElement.class, required = false),
    })
    @XmlAnyElement(lax = true)
    protected MeterReading meterReading;

    @XmlElementRefs({
            @XmlElementRef(name = "IntervalBlock", namespace = "http://naesb.org/espi", type = JAXBElement.class, required = false),
    })
    @XmlAnyElement(lax = true)
    protected List<IntervalBlock> intervalBlocks;

    @XmlElementRefs({
            @XmlElementRef(name = "ElectricPowerUsageSummary", namespace = "http://naesb.org/espi", type = JAXBElement.class, required = false),
    })
    @XmlAnyElement(lax = true)
    protected ElectricPowerUsageSummary electricPowerUsageSummary;

    @XmlElementRefs({
            @XmlElementRef(name = "ElectricPowerQualitySummary", namespace = "http://naesb.org/espi", type = JAXBElement.class, required = false),
    })
    @XmlAnyElement(lax = true)
    private ElectricPowerQualitySummary electricPowerQualitySummary;

    @XmlMixed
    @XmlAnyElement(lax = true)
    protected List<Object> content;

    @XmlAttribute(name = "type")
    protected String type;

    @XmlAttribute(name = "src")
    @XmlSchemaType(name = "anyURI")
    protected String src;

    @XmlAttribute(name = "base", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlSchemaType(name = "anyURI")
    protected String base;

    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    @XmlElementRefs({
            @XmlElementRef(name = "ReadingType", namespace = "http://naesb.org/espi", type = JAXBElement.class, required = false),
    })
    @XmlAnyElement(lax = true)
    protected ReadingType readingType;

    public UsagePoint getUsagePoint() {
        return usagePoint;
    }

    public void setUsagePoint(UsagePoint usagePoint) {
        this.usagePoint = usagePoint;
    }

    public MeterReading getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(MeterReading meterReading) {
        this.meterReading = meterReading;
    }

    public List<IntervalBlock> getIntervalBlocks() {
        return intervalBlocks;
    }

    public void setIntervalBlocks(List<IntervalBlock> intervalBlocks) {
        this.intervalBlocks = intervalBlocks;
    }

    /**
     * The Atom content construct is defined in section 4.1.3 of the format spec.
     * Gets the value of the content property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link String }
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the src property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSrc() {
        return src;
    }

    /**
     * Sets the value of the src property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSrc(String value) {
        this.src = value;
    }

    /**
     * Gets the value of the base property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBase(String value) {
        this.base = value;
    }

    /**
     * Gets the value of the lang property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * <p/>
     * <p/>
     * the map is keyed by the name of the attribute and
     * the value is the string value of the attribute.
     * <p/>
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     * @return always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    public void setReadingType(ReadingType readingType) {
        this.readingType = readingType;
    }

    public ReadingType getReadingType() {
        return readingType;
    }

    public ElectricPowerUsageSummary getElectricPowerUsageSummary() {
        return electricPowerUsageSummary;
    }

    public ElectricPowerQualitySummary getElectricPowerQualitySummary() {
        return electricPowerQualitySummary;
    }

    public void setElectricPowerQualitySummary(ElectricPowerQualitySummary electricPowerQualitySummary) {
        this.electricPowerQualitySummary = electricPowerQualitySummary;
    }
}
