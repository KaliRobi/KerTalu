package com.kertalu.kertalu.users.clients.ktclients;

import com.kertalu.kertalu.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService() {

    }

    public Optional<Client> safeSaveClient(Client client) {
        if (!isClientPresent(client)) {
            return Optional.of(clientRepository.save(client));
        }
        return Optional.empty();
    }

    private boolean isClientPresent(Client client){
        if(clientRepository.findByEmail(client.getEmail()).isEmpty()){
            return false;
        }
        return true;
    }
}
