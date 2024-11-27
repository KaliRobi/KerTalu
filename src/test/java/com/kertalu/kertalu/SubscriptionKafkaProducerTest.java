package com.kertalu.kertalu;

import com.kertalu.kertalu.controllers.datatransferobjects.SubscriptionRequest;
import com.kertalu.kertalu.messaging.SubscriptionKafkaProducer;
import com.kertalu.kertalu.subscription.Subscription;
import com.kertalu.kertalu.subscription.SubscriptionTier;
import com.kertalu.kertalu.users.clients.ktclients.Client;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SubscriptionKafkaProducerTest {

    @Mock
    private KafkaProducer<String, String> mockProducer;

    @InjectMocks
    private SubscriptionKafkaProducer subscriptionKafkaProducer;

    private SubscriptionRequest request;
    private SubscriptionTier subscriptionTier;
    private Client client;
    private Subscription mockSubscription;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new SubscriptionRequest();
        subscriptionTier = new SubscriptionTier("Basic", "basic", true, null);
        client = new Client("testuser", "email@email.com", "92536");
        mockSubscription = new Subscription(Instant.now(), true, subscriptionTier, client);
    }

     @Test
    void testSendSubscriptionEvent_Success() {
        // Prepare expected data
        String expectedMessage = "{\"id\":\"123\", \"tier\":\"Basic\", \"client\":\"testuser\"}";

        KafkaProducer<String, String> mockProducer = mock(KafkaProducer.class);

        SubscriptionKafkaProducer subscriptionKafkaProducer = new SubscriptionKafkaProducer();


        subscriptionKafkaProducer.sendSubscriptionEvent(mockSubscription);


        ArgumentCaptor<ProducerRecord<String, String>> captor = ArgumentCaptor.forClass(ProducerRecord.class);
        verify(mockProducer, times(1)).send(captor.capture(), any());

        ProducerRecord<String, String> capturedRecord = captor.getValue();
        assertEquals("testuser", capturedRecord.key());
        assertEquals(expectedMessage, capturedRecord.value());
    }

    @Test
    void testSendSubscriptionEvent_ExceptionHandling() {
        doThrow(new RuntimeException("Kafka failure")).when(mockProducer).send(any(ProducerRecord.class), any());

        subscriptionKafkaProducer.sendSubscriptionEvent(mockSubscription);

        verify(mockProducer, times(1)).send(any(), any());

    }

    @Test
    void testCloseProducer() {

        subscriptionKafkaProducer.closeProducer();

        verify(mockProducer, times(1)).close();
    }
}
