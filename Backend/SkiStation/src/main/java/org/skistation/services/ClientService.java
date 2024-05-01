package org.skistation.services;

import org.skistation.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService
{
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
