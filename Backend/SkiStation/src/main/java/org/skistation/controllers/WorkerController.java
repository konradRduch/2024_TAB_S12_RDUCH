package org.skistation.controllers;

import org.skistation.models.Worker;
import org.skistation.services.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/workers")
public class WorkerController
{
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }


    @GetMapping("")
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public Worker getWorkerById(@PathVariable("id") Integer id) {
        return workerService.getWorkerById(id).isPresent() ? workerService.getWorkerById(id).get() : null;
    }

    @GetMapping("/{email}")
    public Worker getWorkerByEmail(@PathVariable("email") String email) {
        return workerService.getWorkerByEmail(email).isPresent() ? workerService.getWorkerByEmail(email).get() : null;
    }

    @GetMapping("/{phone}")
    public Worker getWorkerByPhone(@PathVariable("phone") Integer phone) {
        return workerService.getWorkerByPhone(phone).isPresent() ? workerService.getWorkerByPhone(phone).get() : null;
    }

    @PostMapping("/addWorker")
    public String addWorker(@RequestBody Worker worker) {
        workerService.saveWorker(worker);
        return "redirect:/workers";
    }

    @PutMapping("/{id}")
    ResponseEntity<Worker> updateWorker(@RequestBody Worker worker, @PathVariable("id") Integer id) {
        if (workerService.getWorkerById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        worker.setId(id);
        workerService.saveWorker(worker);
        return ResponseEntity.ok(worker);
    }

    @DeleteMapping("/{id}")
    public String deleteWorker(@PathVariable("id") Integer id) {
        workerService.deleteWorker(id);
        return "redirect:/workers";
    }
}
