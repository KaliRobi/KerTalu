package com.kertalu.kertalu.users.userregistration;

import com.kertalu.kertalu.repositories.ClientRepository;
import com.kertalu.kertalu.users.clients.ktclients.Client;

import java.time.Instant;

public class ClientRegistrationInformationService {

        private ClientRepository clientRepository;

        public  Client registerClientFromSubscription(ClientRegistrationInformation clientRegistrationInformation){
            return new Client(
                     clientRegistrationInformation.getName(),
                     clientRegistrationInformation.getEmail(),
                     clientRegistrationInformation.getPhone()

             );
        }
}
