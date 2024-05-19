package org.skistation.models.DTO;

public class BouncePassRequest
{
    Integer liftId;
    Integer passId;

    public BouncePassRequest(Integer liftId, Integer passId) {
        this.liftId = liftId;
        this.passId = passId;
    }

    public Integer getLiftId() {
        return liftId;
    }

    public void setLiftId(Integer liftId) {
        this.liftId = liftId;
    }

    public Integer getPassId() {
        return passId;
    }

    public void setPassId(Integer passId) {
        this.passId = passId;
    }
}
