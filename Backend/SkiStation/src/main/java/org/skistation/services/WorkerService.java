package org.skistation.services;

import org.skistation.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkerService
{
    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }
}
