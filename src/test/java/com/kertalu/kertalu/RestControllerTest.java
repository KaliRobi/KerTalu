package com.kertalu.kertalu;

import com.kertalu.kertalu.controllers.RestController;
import com.kertalu.kertalu.controllers.datatransferobjects.SubscriptionRequest;
import com.kertalu.kertalu.repositories.ClientRepository;
import com.kertalu.kertalu.subscription.Subscription;
import com.kertalu.kertalu.subscription.SubscriptionService;
import com.kertalu.kertalu.subscription.SubscriptionTier;
import com.kertalu.kertalu.subscription.SubscriptionTierService;
import com.kertalu.kertalu.users.clients.ktclients.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private SubscriptionTierService subscriptionTierService;

    @InjectMocks
    private RestController restController;

    private SubscriptionRequest request;
    private SubscriptionTier subscriptionTier;
    private Client client;
    private Subscription mockSubscription;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        mockMvc = MockMvcBuilders.standaloneSetup(restController).build();

        request = new SubscriptionRequest();
        subscriptionTier = new SubscriptionTier("Basic", "basic", true, null);
        client = new Client("testuser", "email@email.com", "92536");
        mockSubscription = new Subscription(Instant.now(), true, subscriptionTier, client);
    }

    @Test
    void testRegisterClientSubscription_Success() throws Exception {

        when(subscriptionService.subscribeClient(any(), anyString())).thenReturn(mockSubscription);

        mockMvc.perform(post("/v1/subscriptions/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"clientRegistrationInformation\": { \"name\": \"testuser\", \"email\": \"email@email.com\", \"phone\": \"92536\" }, \"subscriptionTierId\": \"basic\" }"))
                .andExpect(status().isCreated())  // Expect 201 Created
                .andExpect(content().string(String.valueOf(mockSubscription)));  // Response body contains subscription details

        verify(subscriptionService, times(1)).subscribeClient(any(), anyString());
    }

    @Test
    void testRegisterClientSubscription_Failure() throws Exception {
        when(subscriptionService.subscribeClient(any(), anyString()))
                .thenThrow(new RuntimeException("Subscription failed"));

        mockMvc.perform(post("/v1/subscriptions/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"clientRegistrationInformation\": { \"name\": \"testuser\", \"email\": \"email@email.com\", \"phone\": \"92536\" }, \"subscriptionTierId\": \"basic\" }"))
                .andExpect(status().isBadRequest())  // 400 Bad Request
                .andExpect(content().string("Subscription failed"));

        verify(subscriptionService, times(1)).subscribeClient(any(), anyString());
    }

    @Test
    void testManageSubscriptionTier_Success() throws Exception {
        when(subscriptionTierService.deleteSubscriptionTier(any())).thenReturn(true);

        mockMvc.perform(post("/v1/subscriptions/create-or-modify-tier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Basic\", \"tierCode\": \"basic\", \"active\": true}"))
                .andExpect(status().isCreated())  // 201 Created
                .andExpect(content().string("Subscription saved"));

        verify(subscriptionTierService, times(1)).saveSubscriptionTier(any());
    }

    @Test
    void testManageSubscriptionTier_Failure() throws Exception {
        doThrow(new RuntimeException("Failed to save subscription tier")).when(subscriptionTierService).saveSubscriptionTier(any());


        mockMvc.perform(post("/v1/subscriptions/create-or-modify-tier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Basic\", \"tierCode\": \"basic\", \"active\": true}"))
                .andExpect(status().isBadRequest())  // 400 Bad Request
                .andExpect(content().string("Failed to save subscription tier"));


        verify(subscriptionTierService, times(1)).saveSubscriptionTier(any());
    }

    @Test
    void testDeleteSubscriptionTier_Success() throws Exception {

        when(subscriptionTierService.deleteSubscriptionTier(any())).thenReturn(true);
        mockMvc.perform(post("/v1/subscriptions/delete-tier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Basic\", \"tierCode\": \"basic\", \"active\": true}"))
                .andExpect(status().isCreated())  // 201 Created
                .andExpect(content().string("Subscription deleted"));

        verify(subscriptionTierService, times(1)).deleteSubscriptionTier(any());
    }

    @Test
    void testListAvailableSubscriptions() throws Exception {
        List<SubscriptionTier> subscriptionTiers = Arrays.asList(subscriptionTier);

        when(subscriptionTierService.getSubscriptionList()).thenReturn(subscriptionTiers);

        mockMvc.perform(get("/v1/subscriptions/list-subscription-tier"))
                .andExpect(status().isOk())  // 200 OK
                .andExpect(jsonPath("$[0].name").value("Basic"));

        verify(subscriptionTierService, times(1)).getSubscriptionList();
    }

    @Test
    void testListClients() throws Exception {

        List<Client> clients = Arrays.asList(client);

        when(clientRepository.findAll()).thenReturn(clients);

        mockMvc.perform(get("/v1/client"))
                .andExpect(status().isOk())  // 200 OK
                .andExpect(jsonPath("$[0].name").value("testuser"));

        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetCSRFToken() throws Exception {
        CsrfToken csrfToken = new CsrfToken() {
            @Override
            public String getToken() {
                return "testToken";
            }

            @Override
            public String getHeaderName() {
                return "X-CSRF-TOKEN";
            }

            @Override
            public String getParameterName() {
                return "_csrf";
            }
        };

        mockMvc.perform(get("/v1/csrf-token")
                .sessionAttr(CsrfToken.class.getName(), csrfToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("token").value("testToken"));
    }
}
