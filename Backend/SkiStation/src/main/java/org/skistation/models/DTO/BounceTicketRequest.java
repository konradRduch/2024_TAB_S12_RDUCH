package org.skistation.models.DTO;

/**
 * Represents a request to bounce a ticket.
 * It contains the ID of the lift and the ID of the ticket.
 */
public class BounceTicketRequest
{
    /**
     * The ID of the lift.
     */
    private Integer liftId;

    /**
     * The ID of the ticket.
     */
    private Integer ticketId;

    /**
     * Constructs a new BounceTicketRequest with the specified lift ID and ticket ID.
     *
     * @param liftId   the ID of the lift
     * @param ticketId the ID of the ticket
     */
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