package org.skistation.models.DTO;

import java.time.LocalDateTime;

/**
 * Represents a ticket data transfer object.
 *
 * @param amountOfRides The amount of rides for the ticket.
 * @param pricePerRide  The price per ride for the ticket.
 * @param timeStart     The start date and time of the ticket.
 * @param timeEnd       The end date and time of the ticket.
 */
public record TicketDTO(
        Integer amountOfRides,
        Float pricePerRide,
        LocalDateTime timeStart,
        LocalDateTime timeEnd)
{
}