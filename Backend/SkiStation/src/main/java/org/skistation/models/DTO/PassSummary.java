package org.skistation.models.DTO;

import java.time.Duration;
import java.time.LocalDateTime;

public class PassSummary
{
    Boolean active;
    Float totalDistance;

    String timeLeft;

    public PassSummary(Boolean active, Float totalDistance, String timeLeft) {
        this.active = active;
        this.totalDistance = totalDistance;
        this.timeLeft = timeLeft;
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

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }
}
