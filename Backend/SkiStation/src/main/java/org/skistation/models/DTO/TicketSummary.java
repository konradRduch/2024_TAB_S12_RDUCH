package org.skistation.models.DTO;

import java.time.LocalDateTime;

/**
 * Represents a summary of a ticket.
 * It contains the active status, total distance, amount of rides left, start time, and end time of the ticket.
 */
public class TicketSummary
{
    /**
     * The active status of the ticket.
     */
    Boolean active;

    /**
     * The total distance of the ticket.
     */
    Float totalDistance;

    /**
     * The amount of rides left for the ticket.
     */
    Integer amountOfRidesLeft;

    /**
     * The start time of the ticket.
     */
    LocalDateTime timeStart;

    /**
     * The end time of the ticket.
     */
    LocalDateTime timeEnd;

    /**
     * Constructs a new TicketSummary with the specified active status, total distance, amount of rides left, start time, and end time.
     *
     * @param active            the active status of the ticket
     * @param totalDistance     the total distance of the ticket
     * @param amountOfRidesLeft the amount of rides left for the ticket
     * @param timeStart         the start time of the ticket
     * @param timeEnd           the end time of the ticket
     */
    public TicketSummary(Boolean active, Float totalDistance, Integer amountOfRidesLeft, LocalDateTime timeStart, LocalDateTime timeEnd) {
        this.active = active;
        this.totalDistance = totalDistance;
        this.amountOfRidesLeft = amountOfRidesLeft;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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

    public Integer getAmountOfRidesLeft() {
        return amountOfRidesLeft;
    }

    public void setAmountOfRidesLeft(Integer amountOfRidesLeft) {
        this.amountOfRidesLeft = amountOfRidesLeft;
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
}