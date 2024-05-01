package org.skistation.services;

import org.skistation.repositories.SkiScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class SkiScheduleService
{
    private final SkiScheduleRepository skiScheduleRepository;

    public SkiScheduleService(SkiScheduleRepository skiScheduleRepository) {
        this.skiScheduleRepository = skiScheduleRepository;
    }
}
