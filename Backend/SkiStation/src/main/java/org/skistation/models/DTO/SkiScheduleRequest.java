package org.skistation.models.DTO;

import java.time.LocalDateTime;

/**
 * Represents a ski schedule request.
 * It contains the open time, close time, and lift ID of the ski schedule request.
 */
public class SkiScheduleRequest
{

    /**
     * The open date and time of the ski schedule request.
     */
    private LocalDateTime open;

    /**
     * The close date and time of the ski schedule request.
     */
    private LocalDateTime close;

    /**
     * The lift ID of the ski schedule request.
     */
    private Integer liftId;

    public LocalDateTime getOpen() {
        return open;
    }

    public void setOpen(LocalDateTime open) {
        this.open = open;
    }

    public LocalDateTime getClose() {
        return close;
    }

    public void setClose(LocalDateTime close) {
        this.close = close;
    }

    public Integer getLiftId() {
        return liftId;
    }

    public void setLiftId(Integer liftId) {
        this.liftId = liftId;
    }
}