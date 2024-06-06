package org.skistation.controllers;

import org.skistation.models.Worker;
import org.skistation.services.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing workers.
 * It is responsible for handling requests related to workers.
 */
@RestController
@CrossOrigin
@RequestMapping("/workers")
public class WorkerController
{
    /**
     * Service for managing workers.
     */
    private final WorkerService workerService;

    /**
     * Constructs a new WorkerController with the specified worker service.
     *
     * @param workerService the service for managing workers.
     */
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    /**
     * Gets all workers.
     *
     * @return a list of all workers.
     */
    @GetMapping("")
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    /**
     * Gets a worker by ID.
     *
     * @param id the ID of the worker.
     * @return the worker with the specified ID, or null if no such worker exists.
     */
    @GetMapping("/{id}")
    public Worker getWorkerById(@PathVariable("id") Integer id) {
        return workerService.getWorkerById(id).orElse(null);
    }

    /**
     * Gets a worker by email.
     *
     * @param email the email of the worker.
     * @return the worker with the specified email, or null if no such worker exists.
     */
    @GetMapping("/{email}")
    public Worker getWorkerByEmail(@PathVariable("email") String email) {
        return workerService.getWorkerByEmail(email).orElse(null);
    }

    /**
     * Gets a worker by phone.
     *
     * @param phone the phone number of the worker.
     * @return the worker with the specified phone number, or null if no such worker exists.
     */
    @GetMapping("/{phone}")
    public Worker getWorkerByPhone(@PathVariable("phone") Integer phone) {
        return workerService.getWorkerByPhone(phone).orElse(null);
    }

    /**
     * Adds a new worker.
     *
     * @param worker the worker to add.
     * @return a redirect to the workers page.
     */
    @PostMapping("/addWorker")
    public String addWorker(@RequestBody Worker worker) {
        workerService.saveWorker(worker);
        return "redirect:/workers";
    }

    /**
     * Updates a worker.
     *
     * @param worker the worker to update.
     * @param id     the ID of the worker to update.
     * @return a ResponseEntity containing the updated worker, or a not found status if no such worker exists.
     */
    @PutMapping("/{id}")
    ResponseEntity<Worker> updateWorker(@RequestBody Worker worker, @PathVariable("id") Integer id) {
        if (workerService.getWorkerById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        worker.setId(id);
        workerService.saveWorker(worker);
        return ResponseEntity.ok(worker);
    }

    /**
     * Deletes a worker.
     *
     * @param id the ID of the worker to delete.
     * @return a redirect to the workers page.
     */
    @DeleteMapping("/{id}")
    public String deleteWorker(@PathVariable("id") Integer id) {
        workerService.deleteWorker(id);
        return "redirect:/workers";
    }
}