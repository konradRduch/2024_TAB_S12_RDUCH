package org.skistation.controllers;

import org.skistation.models.Lift;
import org.skistation.services.LiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing lifts.
 * It is responsible for handling requests related to lifts.
 */
@RestController
@CrossOrigin
@RequestMapping("/lifts")
public class LiftController
{
    /**
     * Service for managing lifts.
     */
    private final LiftService liftService;

    /**
     * Constructs a new LiftController with the specified lift service.
     *
     * @param liftService the service for managing lifts.
     */
    public LiftController(LiftService liftService) {
        this.liftService = liftService;
    }

    /**
     * Gets a lift by ID.
     *
     * @param id the ID of the lift.
     * @return the lift with the specified ID.
     */
    @GetMapping("/{id}")
    public Lift getLiftById(@PathVariable Integer id) {
        return liftService.getLiftById(id).get();
    }

    /**
     * Adds a new lift.
     *
     * @param lift the lift to add.
     * @return a redirect to the lifts page.
     */
    @PostMapping("/addLift")
    public String addLift(@RequestBody Lift lift) {
        liftService.saveLift(lift);
        return "redirect:/lifts?success";
    }

    /**
     * Gets all lifts.
     *
     * @return a list of all lifts.
     */
    @GetMapping("")
    public List<Lift> getAllLifts() {
        return liftService.getAllLifts();
    }

    /**
     * Updates a lift.
     *
     * @param updateLift the lift to update.
     * @param id         the ID of the lift to update.
     * @return a ResponseEntity containing the updated lift, or a not found status if no such lift exists.
     */
    @PutMapping("/updateLift/{id}")
    public ResponseEntity<?> updateLift(@RequestBody Lift updateLift, @PathVariable("id") Integer id) {
        if (liftService.getLiftById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updateLift.setId(id);
        liftService.saveLift(updateLift);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a lift.
     *
     * @param id the ID of the lift to delete.
     * @return a redirect to the lifts page.
     */
    @DeleteMapping("/deleteLift/{id}")
    public String deleteLift(@PathVariable("id") Integer id) {
        liftService.deleteLift(id);
        return "redirect:/lifts";
    }
}