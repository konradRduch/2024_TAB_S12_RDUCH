package org.skistation.models.DTO;

import java.time.LocalDateTime;

/**
 * Represents a summary of a pass.
 * It contains the active status, total distance, start time, end time, and number of descents of the pass.
 */
public class PassSummary
{
    /**
     * The active status of the pass.
     */
    Boolean active;

    /**
     * The total distance of the pass.
     */
    Float totalDistance;

    /**
     * The start date and time of the pass.
     */
    LocalDateTime timeStart;

    /**
     * The end date and time of the pass.
     */
    LocalDateTime timeEnd;

    /**
     * The number of descents of the pass.
     */
    Integer descentsNumber;

    /**
     * Constructs a new PassSummary with the specified active status, total distance, start time, end time, and number of descents.
     *
     * @param active         the active status of the pass
     * @param totalDistance  the total distance of the pass
     * @param timeStart      the start time of the pass
     * @param timeEnd        the end time of the pass
     * @param descentsNumber the number of descents of the pass
     */
    public PassSummary(Boolean active, Float totalDistance, LocalDateTime timeStart, LocalDateTime timeEnd, Integer descentsNumber) {
        this.active = active;
        this.totalDistance = totalDistance;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.descentsNumber = descentsNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getDescentsNumber() {
        return descentsNumber;
    }

    public void setDescentsNumber(Integer descentsNumber) {
        this.descentsNumber = descentsNumber;
    }
}