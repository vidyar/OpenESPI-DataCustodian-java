package org.energyos.espi.datacustodian.web.api;
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

import com.sun.syndication.io.FeedException;
import org.energyos.espi.datacustodian.domain.Routes;
import org.energyos.espi.datacustodian.service.IntervalBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IntervalBlockRESTController {

    @Autowired
    private IntervalBlockService intervalBlockService;
    @Autowired
    private AtomService atomService;


    @RequestMapping(value = Routes.DataCustodianRESTIntervalBlockMember, method = RequestMethod.GET)
    public void show(HttpServletResponse response, @PathVariable String usagePointHashedId) throws IOException, FeedException {
//        IntervalBlock intervalBlock = intervalBlockService.findByHashedId(usagePointHashedId);
//        response.setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);
//
//        response.getWriter().print(atomService.entryFor(intervalBlock));
    }

    public void setIntervalBlockService(IntervalBlockService intervalBlockService) {
        this.intervalBlockService = intervalBlockService;
    }

    public void setAtomService(AtomService atomService) {
        this.atomService = atomService;
    }
}
