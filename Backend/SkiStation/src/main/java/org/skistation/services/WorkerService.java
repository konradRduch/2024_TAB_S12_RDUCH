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

    public Optional<Worker> getWorkerById(Integer id) {
        return workerRepository.findById(id);
    }

    public Optional<Worker> getWorkerByEmail(String email) {
        return workerRepository.findByEmail(email);
    }

    public Optional<Worker> getWorkerByPhone(Integer phone) {
        return workerRepository.findByPhone(phone);
    }

    public Optional<Worker> updateWorker(Worker worker) {
        Optional<Worker> workerOptional = workerRepository.findById(worker.getId());
        if (workerOptional.isPresent()) {
            workerRepository.save(worker);
        }
        return workerOptional;
    }

    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public void deleteWorker(Integer id) {
        workerRepository.deleteById(id);
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
}
