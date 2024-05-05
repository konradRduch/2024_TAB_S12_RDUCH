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

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> updateClient(Client client) {
        Optional<Client> clientOptional = clientRepository.findById(client.getId());
        if (clientOptional.isPresent()) {
            clientRepository.save(client);
        }
        return clientOptional;
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public  Client getClientsByEmailAddress(String emailAddress) {
        return clientRepository.findByEmail(emailAddress).get(0);
    }

    public Client getClientsByPhoneNumber(Integer phoneNumber) {
        return clientRepository.findByPhone(phoneNumber).get(0);
    }
}
