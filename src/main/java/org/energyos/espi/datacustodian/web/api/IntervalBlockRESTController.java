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

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.energyos.espi.common.domain.IntervalBlock;
import org.energyos.espi.common.domain.MeterReading;
import org.energyos.espi.common.domain.Routes;
import org.energyos.espi.common.service.ExportService;
import org.energyos.espi.common.service.IntervalBlockService;
import org.energyos.espi.common.service.MeterReadingService;
import org.energyos.espi.common.service.RetailCustomerService;
import org.energyos.espi.common.service.UsagePointService;
import org.energyos.espi.common.utils.ExportFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sun.syndication.io.FeedException;

@Controller
public class IntervalBlockRESTController {

    @Autowired
    private IntervalBlockService intervalBlockService;
    @Autowired
    private RetailCustomerService retailCustomerService;
    @Autowired
    private UsagePointService usagePointService;
    @Autowired
    private MeterReadingService meterReadingService;
    @Autowired
    private ExportService exportService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleGenericException() {}

    // ROOT RESTful forms
    //
    @RequestMapping(value = Routes.ROOT_INTERVAL_BLOCK_COLLECTION, method = RequestMethod.GET)
    public void index(HttpServletResponse response, 
    		@RequestParam Map<String, String> params) throws IOException, FeedException {
        response.setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);
        exportService.exportIntervalBlocks(response.getOutputStream(), new ExportFilter(params));
    }

    // 
    //
    @RequestMapping(value = Routes.ROOT_INTERVAL_BLOCK_MEMBER, method = RequestMethod.GET)
    public void show(HttpServletResponse response, 
    		@PathVariable long intervalBlockId,
    		@RequestParam Map<String, String> params) throws IOException, FeedException {
        response.setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);

        try {
            exportService.exportIntervalBlock(intervalBlockId, response.getOutputStream(), new ExportFilter(params));
                    } catch (Exception e) {
              response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          } 
        }

    // 
    //
    @RequestMapping(value = Routes.ROOT_INTERVAL_BLOCK_COLLECTION, method = RequestMethod.POST)
    public void create(HttpServletResponse response, 
    		@RequestParam Map<String, String> params,
    		InputStream stream) throws IOException {
        response.setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);
        try {
            IntervalBlock intervalBlock = this.intervalBlockService.importResource(stream);
            exportService.exportIntervalBlock(intervalBlock.getId(), response.getOutputStream(), new ExportFilter(params));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    //
    @RequestMapping(value = Routes.ROOT_INTERVAL_BLOCK_MEMBER, method = RequestMethod.PUT)
    public void update(HttpServletResponse response, 
    		@PathVariable long intervalBlockId,
    		@RequestParam Map<String, String> params,
    		InputStream stream) throws IOException, FeedException {
        IntervalBlock intervalBlock = intervalBlockService.findById(intervalBlockId);
 
        if (intervalBlock != null) {
            try {
                intervalBlock.merge(intervalBlockService.importResource(stream));
                intervalBlockService.persist(intervalBlock);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = Routes.ROOT_INTERVAL_BLOCK_MEMBER, method = RequestMethod.DELETE)
    public void delete(HttpServletResponse response, 
    		@PathVariable long intervalBlockId,
    		@RequestParam Map<String, String> params,
    		InputStream stream) throws IOException, FeedException {
        IntervalBlock intervalBlock = intervalBlockService.findById(intervalBlockId);
        if (intervalBlock != null) {
        	intervalBlockService.delete(intervalBlock);
        }
    }



    // XPath RESTful forms 
    //
    @RequestMapping(value = Routes.INTERVAL_BLOCK_COLLECTION, method = RequestMethod.GET)
    public void index(HttpServletResponse response, 
    		@PathVariable long retailCustomerId,
    		@PathVariable long usagePointId,
    		@PathVariable long meterReadingId,
    		@RequestParam Map<String, String> params) throws IOException, FeedException {
        response.setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);
        exportService.exportIntervalBlocks(retailCustomerId, usagePointId, meterReadingId, response.getOutputStream(), new ExportFilter(params));
    }

    // 
    //
    @RequestMapping(value = Routes.INTERVAL_BLOCK_MEMBER, method = RequestMethod.GET)
    public void show(HttpServletResponse response, 
    		@PathVariable long retailCustomerId,
    		@PathVariable long usagePointId,
    		@PathVariable long meterReadingId,
    		@PathVariable long intervalBlockId,
    		@RequestParam Map<String, String> params) throws IOException, FeedException {
        response.setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);
        try {
            exportService.exportIntervalBlock(retailCustomerId, usagePointId, meterReadingId, intervalBlockId, response.getOutputStream(), new ExportFilter(params));
            
                    } catch (Exception e) {
              response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          } 
       }

    // 
    //
    @RequestMapping(value = Routes.INTERVAL_BLOCK_COLLECTION, method = RequestMethod.POST)
    public void create(HttpServletResponse response, 
    		@PathVariable long retailCustomerId,
    		@PathVariable long usagePointId,
    		@PathVariable long meterReadingId,
    		@RequestParam Map<String, String> params,
    		InputStream stream) throws IOException {
        response.setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);
    	MeterReading meterReading = meterReadingService.findById(new Long(retailCustomerId), new Long(usagePointId), new Long(meterReadingId));
        try {
            IntervalBlock intervalBlock = this.intervalBlockService.importResource(stream);
            intervalBlockService.associateByUUID(meterReading, intervalBlock.getUUID());
            exportService.exportIntervalBlock(retailCustomerId, usagePointId, meterReadingId, intervalBlock.getId(), response.getOutputStream(), new ExportFilter(params));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    //
    @RequestMapping(value = Routes.INTERVAL_BLOCK_MEMBER, method = RequestMethod.PUT)
    public void update(HttpServletResponse response, 
    		@PathVariable long retailCustomerId,
    		@PathVariable long usagePointId,
    		@PathVariable long meterReadingId,
    		@PathVariable long intervalBlockId,
    		@RequestParam Map<String, String> params,
    		InputStream stream) throws IOException, FeedException {
        IntervalBlock intervalBlock = intervalBlockService.findById(retailCustomerId, usagePointId, meterReadingId, intervalBlockId);
 
        if (intervalBlock != null) {
            try {
                intervalBlock.merge(intervalBlockService.importResource(stream));
                intervalBlockService.persist(intervalBlock);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = Routes.INTERVAL_BLOCK_MEMBER, method = RequestMethod.DELETE)
    public void delete(HttpServletResponse response, 
       		@PathVariable long retailCustomerId,
    		@PathVariable long usagePointId,
    		@PathVariable long meterReadingId,
    		@PathVariable long intervalBlockId,
    		@RequestParam Map<String, String> params,
    		InputStream stream) throws IOException, FeedException {
        IntervalBlock intervalBlock = intervalBlockService.findById(retailCustomerId, usagePointId, meterReadingId, intervalBlockId);
        if (intervalBlock != null) {
        	intervalBlockService.delete(intervalBlock);
        }
    }

    public void setUsagePointService(UsagePointService usagePointService) {
        this.usagePointService = usagePointService;
    }

    public void setRetailCustomerService(RetailCustomerService retailCustomerService) {
        this.retailCustomerService = retailCustomerService;
    }

    public void setAtomService(IntervalBlockService intervalBlockService) {
        this.intervalBlockService = intervalBlockService;
    }
    
    public void setExportService(ExportService exportService) {
    	this.exportService = exportService;
    }
}
