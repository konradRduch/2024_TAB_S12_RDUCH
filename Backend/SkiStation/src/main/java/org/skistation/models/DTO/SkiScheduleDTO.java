package org.skistation.models.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a ski schedule data transfer object.
 *
 * @param id       The ID of the ski schedule.
 * @param open     The open date and time of the ski schedule.
 * @param close    The close date and time of the ski schedule.
 * @param liftId   The lift ID of the ski schedule.
 * @param liftName The lift name of the ski schedule.
 * @param active   The active status of the ski schedule.
 * @param distance The distance of the lift of the ski schedule
 */
public record SkiScheduleDTO(
        Integer id,
        String open,
        String close,
        Integer liftId,
        String liftName,
        Boolean active,
        Float distance)
{
}