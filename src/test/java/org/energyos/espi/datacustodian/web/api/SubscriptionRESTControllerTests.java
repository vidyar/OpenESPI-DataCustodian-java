package org.energyos.espi.datacustodian.web.api;

import com.sun.syndication.io.FeedException;
import org.energyos.espi.common.domain.RetailCustomer;
import org.energyos.espi.common.domain.Subscription;
import org.energyos.espi.common.models.atom.EntryType;
import org.energyos.espi.common.service.SubscriptionService;
import org.energyos.espi.common.service.UsagePointService;
import org.energyos.espi.datacustodian.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.IOException;

import static org.energyos.espi.common.test.EspiFactory.newSubscription;
import static org.mockito.Mockito.*;

public class SubscriptionRESTControllerTests extends BaseTest {

    @Mock
    private UsagePointService usagePointService;
    @Mock
    private SubscriptionService subscriptionService;
    @Mock
    private ExportWriter exportWriter;
    @Mock
    private ExportService exportService;
    @Mock
    private EntryTypeIterator entryTypeIterator;
    @Mock
    private Jaxb2Marshaller marshaller;

    private MockHttpServletResponse response;
    private RetailCustomer retailCustomer;
    private SubscriptionRESTController controller;
    private Subscription subscription;

    @Before
    public void before() {
        response = new MockHttpServletResponse();
        controller = new SubscriptionRESTController();

        controller.setWriter(exportWriter);
        controller.setExportService(exportService);

        subscription = newSubscription();
        retailCustomer = subscription.getRetailCustomer();
        subscription.setHashedId("hashedId");
    }

    @Test
    public void show_streamsEntries() throws IOException, FeedException, InterruptedException {
        when(exportService.findAllForHashedId(subscription.getHashedId())).thenReturn(entryTypeIterator);

        when(entryTypeIterator.hasNext())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        when(entryTypeIterator.next())
                .thenReturn(new EntryType())
                .thenReturn(new EntryType())
                .thenReturn(null);

        controller.show(response, subscription.getHashedId());

        verify(exportWriter, times(2)).write(any(EntryType.class));
    }
}
