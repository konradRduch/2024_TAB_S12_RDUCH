package org.skistation.services;

import org.skistation.models.Ticket;
import org.skistation.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Represents a ticket service class.
 * It provides methods to perform operations on the ticket table in the database.
 */
@Service
public class TicketService
{
    /**
     * The ticket repository to perform operations on the ticket table in the database.
     */
    private final TicketRepository ticketRepository;

    /**
     * Constructs a new ticket service with the specified ticket repository.
     *
     * @param ticketRepository the ticket repository to perform operations on the ticket table in the database
     */
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * Retrieves a ticket by their ID.
     *
     * @param id the ID of the ticket to retrieve
     * @return an Optional containing the ticket if found, or an empty Optional if not
     */
    public Optional<Ticket> getTicketById(Integer id) {
        return ticketRepository.findById(id);
    }

    /**
     * Modifies a ticket in the database.
     *
     * @param ticket the ticket to modify
     * @return an Optional containing the modified ticket if found, or an empty Optional if not
     */
    public Optional<Ticket> modifyTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getId());
        if (ticketOptional.isPresent()) {
            ticketRepository.save(ticket);
        }
        return ticketOptional;
    }

    /**
     * Saves a ticket to the database.
     *
     * @param ticket the ticket to save
     * @return the saved ticket
     */
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * Deletes a ticket from the database by their ID.
     *
     * @param id the ID of the ticket to delete
     */
    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }

    /**
     * Retrieves all tickets from the database.
     *
     * @return a list of all tickets
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Retrieves all tickets by the specified order ID from the database.
     *
     * @param orderId the ID of the order
     * @return a list of all tickets by the specified order ID
     */
    public List<Ticket> getTicketsByOrderId(Integer orderId) {
        return ticketRepository.findByOrderId(orderId);
    }

    /**
     * Retrieves all tickets by the specified price list ID from the database.
     *
     * @param priceListId the ID of the price list
     * @return a list of all tickets by the specified price list ID
     */
    public List<Ticket> getTicketsByPriceListId(Integer priceListId) {
        return ticketRepository.findByPriceListId(priceListId);
    }

    /**
     * Bounces a ticket by their ID.
     *
     * @param ticketId the ID of the ticket to bounce
     * @return true if the ticket was successfully bounced, false otherwise
     */
    public boolean bounceTicket(Integer ticketId) {
        if (getTicketById(ticketId).isPresent()) {
            Ticket ticket = getTicketById(ticketId).get();
            if (ticket.getAmountOfRides() > 0) {
                ticket.setAmountOfRides(ticket.getAmountOfRides() - 1);
                ticketRepository.save(ticket);
                return true;
            }
        }
        return false;
    }
}