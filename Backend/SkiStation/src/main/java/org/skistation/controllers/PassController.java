package org.skistation.controllers;

import org.skistation.models.Client;
import org.skistation.models.Pass;
import org.skistation.services.PassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing passes.
 * It is responsible for handling requests related to passes.
 */
@RestController
@RequestMapping("/passes")
public class PassController
{
    /**
     * Service for managing passes.
     */
    private final PassService passService;

    /**
     * Constructs a new PassController with the specified pass service.
     *
     * @param passService the service for managing passes.
     */
    public PassController(PassService passService) {
        this.passService = passService;
    }

    /**
     * Gets all passes.
     *
     * @return a list of all passes.
     */
    @GetMapping("")
    public List<Pass> getAllPasss() {
        return passService.getAllPass();
    }

    /**
     * Gets a pass by ID.
     *
     * @param id the ID of the pass.
     * @return the pass with the specified ID.
     */
    @GetMapping("{id}")
    public Pass getPassById(@PathVariable("id") Integer id) {
        return passService.getPassById(id).get();
    }

    /**
     * Adds a new pass.
     *
     * @param ticket the pass to add.
     * @return a redirect to the passes page.
     */
    @PostMapping("/addPass")
    public String addPass(@RequestBody Pass ticket) {
        passService.savePass(ticket);

        return "redirect:/tickets";
    }

    /**
     * Updates a pass.
     *
     * @param toUpdate the pass to update.
     * @param id       the ID of the pass to update.
     * @return a ResponseEntity containing the updated pass, or a not found status if no such pass exists.
     */
    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@RequestBody Pass toUpdate, @PathVariable("id") Integer id) {
        if (passService.getPassById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        passService.savePass(toUpdate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a pass.
     *
     * @param id the ID of the pass to delete.
     * @return a redirect to the passes page.
     */
    @DeleteMapping("/{id}")
    public String deletePass(@PathVariable("id") Integer id) {
        passService.deletePass(id);
        return "redirect:/tickets";
    }

    /**
     * Suspends a pass.
     *
     * @param clientData the client data.
     * @param id         the ID of the pass to suspend.
     * @return a ResponseEntity containing the suspended pass, or a not found status if no such pass exists.
     */
    @PostMapping("/suspend/{id}")
    public ResponseEntity<Pass> suspendPass(@RequestBody Client clientData, @PathVariable("id") Integer id) {
        Optional<Pass> pass = passService.suspendPass(id, clientData.getEmail(), clientData.getPhone());
        return pass.isPresent() ? ResponseEntity.ok(pass.get()) : ResponseEntity.notFound().build();
    }

    /**
     * Resumes a pass.
     *
     * @param clientData the client data.
     * @param id         the ID of the pass to resume.
     * @return a ResponseEntity containing the resumed pass, or a not found status if no such pass exists.
     */
    @PostMapping("/resume/{id}")
    public ResponseEntity<Pass> resumePass(@RequestBody Client clientData, @PathVariable("id") Integer id) {
        Optional<Pass> pass = passService.resumePass(id, clientData.getEmail(), clientData.getPhone());
        return pass.isPresent() ? ResponseEntity.ok(pass.get()) : ResponseEntity.notFound().build();
    }

}