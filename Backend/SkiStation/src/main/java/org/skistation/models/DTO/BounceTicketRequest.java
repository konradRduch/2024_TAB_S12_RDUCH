package org.skistation.models.DTO;


public class BounceTicketRequest {
    Integer liftId;
    Integer ticketId;

    public BounceTicketRequest(Integer liftId, Integer ticketId) {
        this.liftId = liftId;
        this.ticketId = ticketId;
    }

    public Integer getLiftId() {
        return liftId;
    }

    public void setLiftId(Integer liftId) {
        this.liftId = liftId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}
