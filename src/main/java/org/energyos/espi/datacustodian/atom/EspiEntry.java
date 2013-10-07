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

package org.energyos.espi.datacustodian.atom;

import org.energyos.espi.datacustodian.domain.IdentifiedObject;
import org.energyos.espi.datacustodian.models.atom.ContentType;
import org.energyos.espi.datacustodian.models.atom.EntryType;
import org.energyos.espi.datacustodian.models.atom.IdType;
import org.energyos.espi.datacustodian.models.atom.LinkType;
import org.energyos.espi.datacustodian.utils.EspiMarshaller;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public abstract class EspiEntry<T extends IdentifiedObject> extends EntryType {
    private LinkType selfLink = new LinkType();
    private LinkType upLink = new LinkType();
    private List<LinkType> relatedLinks = new ArrayList<>();

    protected T espiObject;

    public EspiEntry(T espiObject) {
        this.espiObject = espiObject;
        this.setTitle(espiObject.getDescription());



        IdType entryId = new IdType();
        entryId.setValue(espiObject.getMRID());
        this.setId(entryId);

        this.setPublished(new DateTime(espiObject.getCreated()));
        this.setUpdated(new DateTime(espiObject.getUpdated()));

        selfLink.setRel("self");
        selfLink.setHref(getSelfHref());
        upLink.setRel("up");
        upLink.setHref(getUpHref());

        getLinks().add(selfLink);
        getLinks().add(upLink);

        buildRelatedLinks();

        ContentType content = new ContentType();
        content.setEntity(espiObject);
        this.setContent(content);
    }

    protected abstract String getSelfHref();
    protected abstract String getUpHref();
    protected abstract void buildRelatedLinks();

    public LinkType getSelfLink() {
        return selfLink;
    }

    public LinkType getUpLink() {
        return upLink;
    }

    public List<LinkType> getRelatedLinks() {
        return relatedLinks;
    }

    protected void addRelatedLink(String href) {
        LinkType link = new LinkType();
        link.setRel("related");
        link.setHref(href);

        relatedLinks.add(link);
        getLinks().add(link);
    }

    protected void setUpLinkHref(String href) {
        getUpLink().setHref(href);
    }

    protected void setSelfLinkHref(String href) {
        getSelfLink().setHref(href);
    }
}