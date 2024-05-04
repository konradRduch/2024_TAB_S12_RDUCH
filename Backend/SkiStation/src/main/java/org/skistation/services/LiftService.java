package org.skistation.services;

import org.skistation.models.Lift;
import org.skistation.repositories.LiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiftService
{
    private final LiftRepository liftRepository;

    public LiftService(LiftRepository liftRepository) {
        this.liftRepository = liftRepository;
    }

    Optional<Lift> getLiftById(Integer id) {
        return liftRepository.findById(id);
    }

    Optional<Lift> modifyLift(Lift lift) {
        Optional<Lift> liftOptional = liftRepository.findById(lift.getId());
        if (liftOptional.isPresent()) {
            liftRepository.save(lift);
        }
        return liftOptional;
    }

    Lift saveLift(Lift lift) {
        return liftRepository.save(lift);
    }

    void deleteLift(Integer id) {
        liftRepository.deleteById(id);
    }

    List<Lift> getAllLifts() {
        return liftRepository.findAll();
    }
}
