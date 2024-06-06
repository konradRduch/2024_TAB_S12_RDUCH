package org.skistation.models.DTO;

/**
 * Represents a request to bounce a pass.
 * It contains the ID of the lift and the ID of the pass.
 */
public class BouncePassRequest
{
    /**
     * The ID of the lift.
     */
    private Integer liftId;

    /**
     * The ID of the pass.
     */
    private Integer passId;

    /**
     * Constructs a new BouncePassRequest with the specified lift ID and pass ID.
     *
     * @param liftId the ID of the lift
     * @param passId the ID of the pass
     */
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