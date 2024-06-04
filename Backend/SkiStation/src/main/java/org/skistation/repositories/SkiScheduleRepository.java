package org.skistation.repositories;

import org.skistation.models.SkiSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Represents a ski schedule repository interface extending JpaRepository.
 * It provides methods to perform queries on the ski schedule table in the database.
 */
@Repository
public interface SkiScheduleRepository extends JpaRepository<SkiSchedule, Integer>
{
    /**
     * Finds ski schedules by their lift ID.
     *
     * @param liftId the lift ID to search for
     * @return a list of ski schedules with the specified lift ID
     */
    List<SkiSchedule> findByLiftId(int liftId);
}