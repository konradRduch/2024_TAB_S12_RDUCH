package org.skistation.services;

import org.skistation.models.Lift;
import org.skistation.repositories.LiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Represents a lift service class.
 * It provides methods to perform operations on the lift table in the database.
 */
@Service
public class LiftService
{
    /**
     * The lift repository to perform operations on the lift table in the database.
     */
    private final LiftRepository liftRepository;

    /**
     * Constructs a new lift service with the specified lift repository.
     *
     * @param liftRepository the lift repository to perform operations on the lift table in the database
     */
    public LiftService(LiftRepository liftRepository) {
        this.liftRepository = liftRepository;
    }

    /**
     * Retrieves a lift by their ID.
     *
     * @param id the ID of the lift to retrieve
     * @return an Optional containing the lift if found, or an empty Optional if not
     */
    public Optional<Lift> getLiftById(Integer id) {
        return liftRepository.findById(id);
    }

    /**
     * Modifies a lift in the database.
     *
     * @param lift the lift to modify
     * @return an Optional containing the modified lift if found, or an empty Optional if not
     */
    public Optional<Lift> modifyLift(Lift lift) {
        Optional<Lift> liftOptional = liftRepository.findById(lift.getId());
        if (liftOptional.isPresent()) {
            liftRepository.save(lift);
        }
        return liftOptional;
    }

    /**
     * Saves a lift to the database.
     *
     * @param lift the lift to save
     * @return the saved lift
     */
    public Lift saveLift(Lift lift) {
        return liftRepository.save(lift);
    }

    /**
     * Deletes a lift from the database by their ID.
     *
     * @param id the ID of the lift to delete
     */
    public void deleteLift(Integer id) {
        liftRepository.deleteById(id);
    }

    /**
     * Retrieves all lifts from the database.
     *
     * @return a list of all lifts
     */
    public List<Lift> getAllLifts() {
        return liftRepository.findAll();
    }
}