package org.skistation.services;

import org.skistation.repositories.PassRepository;
import org.springframework.stereotype.Service;

@Service
public class PassService
{
    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }
}
