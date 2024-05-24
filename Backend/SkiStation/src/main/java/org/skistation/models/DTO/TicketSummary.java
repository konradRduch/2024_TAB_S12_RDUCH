package org.skistation.models.DTO;

public class TicketSummary {
    Boolean active;
    Float totalDistance;
    Integer amountOfRidesLeft;

    public TicketSummary(Boolean active, Float totalDistance, Integer amountOfRidesLeft) {
        this.active = active;
        this.totalDistance = totalDistance;
        this.amountOfRidesLeft = amountOfRidesLeft;
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
}
