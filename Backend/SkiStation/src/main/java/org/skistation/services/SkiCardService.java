package org.skistation.services;

import org.skistation.repositories.SkiCardRepository;
import org.springframework.stereotype.Service;

@Service
public class SkiCardService
{
    private final SkiCardRepository skiCardRepository;

    public SkiCardService(SkiCardRepository skiCardRepository) {
        this.skiCardRepository = skiCardRepository;
    }
}
