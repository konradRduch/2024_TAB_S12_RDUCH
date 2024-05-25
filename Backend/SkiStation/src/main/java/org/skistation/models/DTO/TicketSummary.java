package org.skistation.models.DTO;

import java.time.LocalDateTime;

public class TicketSummary {
    Boolean active;
    Float totalDistance;
    Integer amountOfRidesLeft;
    LocalDateTime timeStart;
    LocalDateTime timeEnd;

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
