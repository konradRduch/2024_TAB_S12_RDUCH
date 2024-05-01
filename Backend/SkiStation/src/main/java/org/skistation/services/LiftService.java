package org.skistation.services;

import org.skistation.repositories.LiftRepository;
import org.springframework.stereotype.Service;

@Service
public class LiftService
{
    private final LiftRepository liftRepository;

    public LiftService(LiftRepository liftRepository) {
        this.liftRepository = liftRepository;
    }
}
