package org.skistation.models.DTO;

import java.time.Duration;
import java.time.LocalDateTime;

public class PassSummary
{
    Boolean active;
    Float totalDistance;

   LocalDateTime timeStart;
   LocalDateTime timeEnd;

   Integer descentsNumber;

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
