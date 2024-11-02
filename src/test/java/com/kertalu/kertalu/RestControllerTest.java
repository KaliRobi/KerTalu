package com.kertalu.kertalu;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kertalu.kertalu.controllers.RestController;
import com.kertalu.kertalu.controllers.datatransferobjects.SubscriptionRequest;
import com.kertalu.kertalu.repositories.ClientRepository;
import com.kertalu.kertalu.subscription.Subscription;
import com.kertalu.kertalu.subscription.SubscriptionService;
import com.kertalu.kertalu.subscription.SubscriptionTier;
import com.kertalu.kertalu.users.clients.ktclients.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private RestController restController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testRegisterClientSubscription_Success() throws Exception {
        SubscriptionRequest request = new SubscriptionRequest();
        Client client1 = new Client(1L, Instant.now(), Instant.now(), "Jon Doe", "jon.doe@message.com", "+3725555500");
        SubscriptionTier tier1 = new SubscriptionTier(55L, Instant.now(), "test tier", "tier tier", true, null );
        request.setClient(client1);
        request.setSubscriptionTierId(tier1.getId());

        Subscription subscription = new Subscription(Instant.now(), true, tier1, client1);

        when(subscriptionService.subscribeClient(client1, tier1.getId())).thenReturn(subscription);

        mockMvc.perform(post("/v1/subscriptions/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"client\":\"client1\", \"subscriptionTierId\":\"tier1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.client").value("client1"))
                .andExpect(jsonPath("$.tier").value("tier1"));
    }

    @Test
    public void testRegisterClientSubscription_Failure() throws Exception {
        Client client1 = new Client(1L, Instant.now(), Instant.now(), "Jon Doe", "jon.doe@message.com", "+3725555500");
        SubscriptionTier tier1 = new SubscriptionTier(55L, Instant.now(), "test tier", "tier tier", true, null );
        when(subscriptionService.subscribeClient(client1, tier1.getId())).thenThrow(new RuntimeException("Error occurred"));

        mockMvc.perform(post("/v1/subscriptions/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"client\":\"client1\", \"subscriptionTierId\":\"tier1\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterClientSubscription_Modify_Success() throws Exception {
        SubscriptionRequest request = new SubscriptionRequest();
        Client client1 = new Client(1L, Instant.now(), Instant.now(), "Jon Doe", "jon.doe@message.com", "+3725555500");
        request.setClient(client1);

        when(subscriptionService.getClientSubscription(client1)).thenReturn(null);

        mockMvc.perform(post("/v1/subscriptions/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"client\":\"client1\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterClientSubscription_Modify_Failure() throws Exception {
        SubscriptionRequest request = new SubscriptionRequest();
        Client client1 = new Client(1L, Instant.now(), Instant.now(), "Jon Doe", "jon.doe@message.com", "+3725555500");
        request.setClient(client1);

        when(subscriptionService.getClientSubscription(client1)).thenThrow(new RuntimeException("Error occurred"));

        mockMvc.perform(post("/v1/subscriptions/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"client\":\"client1\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testListClients() throws Exception {
        Client client1 = new Client(1L, Instant.now(), Instant.now(), "Jon Doe", "jon.doe@message.com", "+3725555500");
        Client client2 = new Client(2L, Instant.now(), Instant.now(), "Jane Doe", "jane.doe@message.com", "+3725555500");
        List<Client> clients = Arrays.asList(client1, client2);

        when(clientRepository.findAll()).thenReturn(clients);

        mockMvc.perform(get("/v1/client"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("client1"))
                .andExpect(jsonPath("$[1].name").value("client2"));
    }

}


