package org.skistation.services;

import org.skistation.models.Client;
import org.skistation.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService
{
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    Optional<Client> modifyClient(Client client) {
        Optional<Client> clientOptional = clientRepository.findById(client.getId());
        if (clientOptional.isPresent()) {
            clientRepository.save(client);
        }
        return clientOptional;
    }

    Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    List<Client> getClientsByEmailAddress(String emailAddress) {
        return clientRepository.findByEmail(emailAddress);
    }

    List<Client> getClientsByPhoneNumber(Integer phoneNumber) {
        return clientRepository.findByPhone(phoneNumber);
    }
}
