package org.energyos.espi.datacustodian.domain;

import org.energyos.espi.datacustodian.atom.XMLTest;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class IdentifiedObjectTests extends XMLTest {

    @Test
    public void mrid() {
        IdentifiedObject identifiedObject = new IdentifiedObject();

        identifiedObject.setMRID("urn:uuid:E8E75691-7F9D-49F3-8BE2-3A74EBF6BFC0");
        assertEquals("urn:uuid:E8E75691-7F9D-49F3-8BE2-3A74EBF6BFC0", identifiedObject.getMRID());
        assertEquals(UUID.fromString("E8E75691-7F9D-49F3-8BE2-3A74EBF6BFC0"), identifiedObject.getUUID());
    }

    @Test
    public void mrid_givenNoUUID() {
        IdentifiedObject identifiedObject = new IdentifiedObject();

        assertEquals(null, identifiedObject.getMRID());
    }

    @Test
    public void mrid_isCaseInsensitive() {
        IdentifiedObject identifiedObject = new IdentifiedObject();

        identifiedObject.setMRID("urn:uuid:e8e75691-7f9d-49f3-8be2-3a74ebf6bfc0");
        assertEquals("urn:uuid:E8E75691-7F9D-49F3-8BE2-3A74EBF6BFC0", identifiedObject.getMRID());
    }

    @Test
    public void UUID() {
        IdentifiedObject identifiedObject = new IdentifiedObject();
        UUID uuid = UUID.randomUUID();

        identifiedObject.setUUID(uuid);
        assertEquals(uuid, identifiedObject.getUUID());
        assertEquals("urn:uuid:" + uuid.toString().toUpperCase(), identifiedObject.getMRID());
    }

}
