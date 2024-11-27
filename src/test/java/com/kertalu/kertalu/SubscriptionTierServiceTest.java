
package com.kertalu.kertalu;

import com.kertalu.kertalu.repositories.SubscriptionTierRepository;
import com.kertalu.kertalu.subscription.SubscriptionTier;
import com.kertalu.kertalu.subscription.SubscriptionTierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubscriptionTierServiceTest {

    @Mock
    private SubscriptionTierRepository subscriptionTierRepository;

    @InjectMocks
    private SubscriptionTierService subscriptionTierService;

    // This method runs before each test to set up the mocks
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //  Mockito annotations
    }

    // Test for the findSubscriptionTier method
    @Test
    void testFindSubscriptionTier() {
        String tierName = "Basic";
        SubscriptionTier mockTier = new SubscriptionTier(tierName, null, true, null);

        when(subscriptionTierRepository.findTierByName(tierName)).thenReturn(Optional.of(mockTier));

        Optional<SubscriptionTier> result = subscriptionTierService.findSubscriptionTier(tierName);

        assertTrue(result.isPresent()); //  result is present
        assertEquals(tierName, result.get().getName()); //  name matches

        verify(subscriptionTierRepository, times(1)).findTierByName(tierName);
    }

    // Test for the deleteSubscriptionTier method when it succeeds
    @Test
    void testDeleteSubscriptionTier_Success() {
        SubscriptionTier mockTier = new SubscriptionTier();


        doNothing().when(subscriptionTierRepository).delete(mockTier);

        boolean result = subscriptionTierService.deleteSubscriptionTier(mockTier);

        assertTrue(result);

        verify(subscriptionTierRepository, times(1)).delete(mockTier);
    }

    // Test for the deleteSubscriptionTier method when it fails
    @Test
    void testDeleteSubscriptionTier_Failure() {
        SubscriptionTier mockTier = new SubscriptionTier();

        doThrow(new RuntimeException("Delete failed")).when(subscriptionTierRepository).delete(mockTier);

        boolean result = subscriptionTierService.deleteSubscriptionTier(mockTier);

        assertFalse(result);

        verify(subscriptionTierRepository, times(1)).delete(mockTier);
    }

    // Test for the saveSubscriptionTier method when it succeeds
    @Test
    void testSaveSubscriptionTier_Success() {
        SubscriptionTier mockTier = new SubscriptionTier();

        when(subscriptionTierRepository.save(mockTier)).thenReturn(mockTier);

        boolean result = subscriptionTierService.saveSubscriptionTier(mockTier);

        assertTrue(result);
        verify(subscriptionTierRepository, times(1)).save(mockTier);
    }

    // Test for the saveSubscriptionTier method when it fails
    @Test
    void testSaveSubscriptionTier_Failure() {
        SubscriptionTier mockTier = new SubscriptionTier();

        doThrow(new RuntimeException("Save failed")).when(subscriptionTierRepository).save(mockTier);

        boolean result = subscriptionTierService.saveSubscriptionTier(mockTier);

        assertFalse(result);
        verify(subscriptionTierRepository, times(1)).save(mockTier);
    }

    // Test for the getSubscriptionList method
    @Test
    void testGetSubscriptionList() {
        List<SubscriptionTier> mockList = new ArrayList<>();
        mockList.add(new SubscriptionTier());
        mockList.add(new SubscriptionTier());

        when(subscriptionTierRepository.findAll()).thenReturn(mockList);

        List<SubscriptionTier> result = subscriptionTierService.getSubscriptionList();

        assertEquals(2, result.size()); // Ensure the list has the correct number of elements

        verify(subscriptionTierRepository, times(1)).findAll();
    }
}
