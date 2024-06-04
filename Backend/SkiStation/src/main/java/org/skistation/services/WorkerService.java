package org.skistation.services;

import org.skistation.models.Worker;
import org.skistation.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Represents a worker service class.
 * It provides methods to perform operations on the worker table in the database.
 */
@Service
public class WorkerService
{
    /**
     * The worker repository to perform operations on the worker table in the database.
     */
    private final WorkerRepository workerRepository;

    /**
     * Constructs a new worker service with the specified worker repository.
     *
     * @param workerRepository the worker repository to perform operations on the worker table in the database
     */
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    /**
     * Retrieves a worker by their ID.
     *
     * @param id the ID of the worker to retrieve
     * @return an Optional containing the worker if found, or an empty Optional if not
     */
    public Optional<Worker> getWorkerById(Integer id) {
        return workerRepository.findById(id);
    }

    /**
     * Retrieves a worker by their email.
     *
     * @param email the email of the worker to retrieve
     * @return an Optional containing the worker if found, or an empty Optional if not
     */
    public Optional<Worker> getWorkerByEmail(String email) {
        return workerRepository.findByEmail(email);
    }

    /**
     * Retrieves a worker by their phone.
     *
     * @param phone the phone of the worker to retrieve
     * @return an Optional containing the worker if found, or an empty Optional if not
     */
    public Optional<Worker> getWorkerByPhone(Integer phone) {
        return workerRepository.findByPhone(phone);
    }

    /**
     * Modifies a worker in the database.
     *
     * @param worker the worker to modify
     * @return an Optional containing the modified worker if found, or an empty Optional if not
     */
    public Optional<Worker> updateWorker(Worker worker) {
        Optional<Worker> workerOptional = workerRepository.findById(worker.getId());
        if (workerOptional.isPresent()) {
            workerRepository.save(worker);
        }
        return workerOptional;
    }

    /**
     * Saves a worker to the database.
     *
     * @param worker the worker to save
     * @return the saved worker
     */
    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    /**
     * Deletes a worker from the database by their ID.
     *
     * @param id the ID of the worker to delete
     */
    public void deleteWorker(Integer id) {
        workerRepository.deleteById(id);
    }

    /**
     * Retrieves all workers from the database.
     *
     * @return a list of all workers
     */
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
}