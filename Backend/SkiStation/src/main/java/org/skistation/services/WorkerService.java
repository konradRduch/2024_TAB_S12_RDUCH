package org.skistation.services;

import org.skistation.models.Worker;
import org.skistation.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService
{
    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    Optional<Worker> getWorkerById(Integer id) {
        return workerRepository.findById(id);
    }

    Optional<Worker> modifyWorker(Worker worker) {
        Optional<Worker> workerOptional = workerRepository.findById(worker.getId());
        if (workerOptional.isPresent()) {
            workerRepository.save(worker);
        }
        return workerOptional;
    }

    Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    void deleteWorker(Integer id) {
        workerRepository.deleteById(id);
    }

    List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
}
