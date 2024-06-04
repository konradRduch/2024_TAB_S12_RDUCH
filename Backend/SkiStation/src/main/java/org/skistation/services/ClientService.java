package org.skistation.services;

import org.skistation.models.Client;
import org.skistation.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Represents a client service class.
 * It provides methods to perform operations on the client table in the database.
 */
@Service
public class ClientService
{
    /**
     * The client repository to perform operations on the client table in the database.
     */
    private final ClientRepository clientRepository;

    /**
     * Constructs a new client service with the specified client repository.
     *
     * @param clientRepository the client repository to perform operations on the client table in the database
     */
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Retrieves a client by their ID.
     *
     * @param id the ID of the client to retrieve
     * @return an Optional containing the client if found, or an empty Optional if not
     */
    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    /**
     * Updates a client in the database.
     *
     * @param client the client to update
     * @return an Optional containing the updated client if found, or an empty Optional if not
     */
    public Optional<Client> updateClient(Client client) {
        Optional<Client> clientOptional = clientRepository.findById(client.getId());
        if (clientOptional.isPresent()) {
            clientRepository.save(client);
        }
        return clientOptional;
    }

    /**
     * Saves a client to the database.
     *
     * @param client the client to save
     * @return the saved client
     */
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    /**
     * Deletes a client from the database by their ID.
     *
     * @param id the ID of the client to delete
     */
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of all clients
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Retrieves a client by their email address.
     *
     * @param emailAddress the email address of the client to retrieve
     * @return the client if found, or null if not
     */
    public Client getClientsByEmailAddress(String emailAddress) {
        List<Client> clients = clientRepository.findByEmail(emailAddress);
        if (clients.isEmpty()) {
            return null;
        }
        return clients.get(0);
    }

    /**
     * Retrieves a client by their phone number.
     *
     * @param phoneNumber the phone number of the client to retrieve
     * @return the client if found, or null if not
     */
    public Client getClientsByPhoneNumber(Integer phoneNumber) {
        List<Client> clients = clientRepository.findByPhone(phoneNumber);
        if (clients.isEmpty()) {
            return null;
        }
        return clients.get(0);
    }

    /**
     * Retrieves a client by their email address and phone number.
     *
     * @param emailAddress the email address of the client to retrieve
     * @param phone        the phone number of the client to retrieve
     * @return the client if found, or null if not
     */
    public Client getClientByEmailAndPhone(String emailAddress, Integer phone) {
        List<Client> clients = clientRepository.findByEmailAndPhone(emailAddress, phone);
        if (clients.isEmpty()) {
            return null;
        }
        return clients.get(0);
    }
}