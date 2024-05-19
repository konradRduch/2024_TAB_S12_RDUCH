package org.skistation.models.DTO;

public class PassSummary
{
    Boolean active;
    Float totalDistance;

    public PassSummary(Boolean active, Float totalDistance) {
        this.active = active;
        this.totalDistance = totalDistance;
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
}
