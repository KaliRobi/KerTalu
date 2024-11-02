package com.kertalu.kertalu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.kertalu.kertalu.kertaluservices.KtService;
import com.kertalu.kertalu.repositories.SubscriptionRepository;
import com.kertalu.kertalu.repositories.SubscriptionTierRepository;
import com.kertalu.kertalu.subscription.Subscription;
import com.kertalu.kertalu.subscription.SubscriptionService;
import com.kertalu.kertalu.subscription.SubscriptionTier;
import com.kertalu.kertalu.users.clients.ktclients.Client;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

public class SubscriptionServiceTest {

    @InjectMocks
    private SubscriptionService subscriptionService;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private SubscriptionTierRepository subscriptionTierRepository;

    private Client client;
    private SubscriptionTier subscriptionTier;
    private Subscription subscription;
    private KtService ktService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
        client = new Client(2L, Instant.now(), Instant.now(), "Jane Doe", "jane.doe@message.com", "+3725555500");
        ktService = new KtService(1L, Instant.now(), true, "SR1", "this is a dummy service object");
        ArrayList<KtService> serviceList = new ArrayList<>();
        serviceList.add(ktService);
        subscriptionTier = new SubscriptionTier(55L, Instant.now(), "test tier", "tier tier", true, serviceList);
        subscription = new Subscription(Instant.now(), true, subscriptionTier, client);
    }

    @Test
    public void testSubscribeClient_Success() throws Exception {

        when(subscriptionTierRepository.findById(55L)).thenReturn(Optional.of(subscriptionTier));
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription result = subscriptionService.subscribeClient(client, 55L);

        assertNotNull(result);
        assertEquals(client, result.getClient());
        assertEquals(subscriptionTier, result.getTier());
        verify(subscriptionTierRepository).findById(55L);
        verify(subscriptionRepository).save(any(Subscription.class));
    }
    @Test
    public void testSubscribeClient_TierNotFound() {
        when(subscriptionTierRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            subscriptionService.subscribeClient(client, 1L);
        });

        assertEquals("Tier not found", exception.getMessage());
        verify(subscriptionRepository, never()).save(any(Subscription.class));
    }

    @Test
    public void testGetClientSubscription() {
        when(subscriptionRepository.findByClient(client)).thenReturn(subscription);

        Subscription result = subscriptionService.getClientSubscription(client);

        assertNotNull(result);
        assertEquals(client, result.getClient());
        verify(subscriptionRepository).findByClient(client);
    }

    @Test
    public void testGetClientSubscription_NotFound() {
        when(subscriptionRepository.findByClient(client)).thenReturn(null);

        Subscription result = subscriptionService.getClientSubscription(client);

        assertNull(result);
        verify(subscriptionRepository).findByClient(client);
    }

    @Test
    public void testHasFeatureAccess_Success() {
        KtService ktService = new KtService(1L, Instant.now(), true, "SR1", "this is a dummy service object" );
        when(subscriptionRepository.findByClient(client)).thenReturn(subscription);

        boolean hasAccess = subscriptionService.hasFeatureAccess(client, ktService);

        assertTrue(hasAccess);
        verify(subscriptionRepository).findByClient(client);
    }

    @Test
    public void testHasFeatureAccess_NoAccess() {
        KtService ktService = new KtService(1L, Instant.now(), true, "SR1", "this is a dummy service object" );
        when(subscriptionRepository.findByClient(client)).thenReturn(subscription);

        boolean hasAccess = subscriptionService.hasFeatureAccess(client, ktService);

        assertFalse(hasAccess);
        verify(subscriptionRepository).findByClient(client);
    }
}
