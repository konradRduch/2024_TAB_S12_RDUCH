package org.skistation.models.DTO;

import java.time.LocalDateTime;

/**
 * Represents a pass data transfer object.
 *
 * @param active The active status of the pass. Passes are active if they are not suspended or expired.
 * @param passTypeName The type name of the pass.
 * @param timeStart The start time of the pass.
 * @param timeEnd The end time of the pass.
 */
public record PassDTO(
        Boolean active,
        String passTypeName,
        LocalDateTime timeStart,
        LocalDateTime timeEnd) {
}