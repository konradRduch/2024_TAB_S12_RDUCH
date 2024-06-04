package org.skistation.models;

import jakarta.persistence.*;

/**
 * Represents a lift ticket entity.
 * It is a many-to-many relationship between lifts and tickets.
 * It is used to store the history of ticket and lift usage.
 */
@Entity
@Table(name = "lift_ticket")
public class LiftTicket
{
    /**
     * The ID of the lift ticket.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * The lift associated with the lift ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lift_id")
    private Lift lift;

    /**
     * The ticket associated with the lift ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public LiftTicket() {
    }

    /**
     * Constructs a new lift ticket with the specified lift and ticket.
     *
     * @param lift   the lift associated with the lift ticket
     * @param ticket the ticket associated with the lift ticket
     */
    public LiftTicket(Lift lift, Ticket ticket) {
        this.lift = lift;
        this.ticket = ticket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}