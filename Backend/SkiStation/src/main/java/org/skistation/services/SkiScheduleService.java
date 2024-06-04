package org.skistation.services;

import org.skistation.models.DTO.SkiScheduleDTO;
import org.skistation.models.SkiSchedule;
import org.skistation.repositories.SkiScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Represents a ski schedule service class.
 * It provides methods to perform operations on the ski schedule table in the database.
 */
@Service
public class SkiScheduleService
{
    /**
     * The ski schedule repository to perform operations on the ski schedule table in the database.
     */
    private final SkiScheduleRepository skiScheduleRepository;

    /**
     * Constructs a new ski schedule service with the specified ski schedule repository.
     *
     * @param skiScheduleRepository the ski schedule repository to perform operations on the ski schedule table in the database
     */
    public SkiScheduleService(SkiScheduleRepository skiScheduleRepository) {
        this.skiScheduleRepository = skiScheduleRepository;
    }

    /**
     * Retrieves a ski schedule by their ID.
     *
     * @param id the ID of the ski schedule to retrieve
     * @return an Optional containing the ski schedule if found, or an empty Optional if not
     */
    public Optional<SkiSchedule> getSkiScheduleById(Integer id) {
        return skiScheduleRepository.findById(id);
    }

    /**
     * Modifies a ski schedule in the database.
     *
     * @param skiSchedule the ski schedule to modify
     * @return an Optional containing the modified ski schedule if found, or an empty Optional if not
     */
    public Optional<SkiSchedule> updateSkiSchedule(SkiSchedule skiSchedule) {
        Optional<SkiSchedule> skiScheduleOptional = skiScheduleRepository.findById(skiSchedule.getId());
        if (skiScheduleOptional.isPresent()) {
            skiScheduleRepository.save(skiSchedule);
        }
        return skiScheduleOptional;
    }

    /**
     * Saves a ski schedule to the database.
     *
     * @param skiSchedule the ski schedule to save
     * @return the saved ski schedule
     */
    public SkiSchedule saveSkiSchedule(SkiSchedule skiSchedule) {
        return skiScheduleRepository.save(skiSchedule);
    }

    /**
     * Deletes a ski schedule from the database by their ID.
     *
     * @param id the ID of the ski schedule to delete
     */
    public void deleteSkiSchedule(Integer id) {
        skiScheduleRepository.deleteById(id);
    }

    /**
     * Retrieves all ski schedules from the database.
     *
     * @return a list of all ski schedules
     */
    public List<SkiSchedule> getAllSkiSchedules() {
        return skiScheduleRepository.findAll();
    }

    /**
     * Retrieves all ski schedules by the specified lift ID from the database.
     *
     * @param liftId the ID of the lift
     * @return a list of all ski schedules by the specified lift ID
     */
    public List<SkiSchedule> getSkiSchedulesByLiftId(Integer liftId) {
        return skiScheduleRepository.findByLiftId(liftId);
    }
}