package org.skistation.controllers;

import java.util.List;

import org.skistation.models.Ticket;
import org.skistation.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing tickets.
 * It is responsible for handling requests related to tickets.
 */
@RestController
@RequestMapping("/tickets")
public class TicketController
{
    /**
     * Service for managing tickets.
     */
    private final TicketService ticketService;

    /**
     * Constructs a new TicketController with the specified ticket service.
     *
     * @param ticketService the service for managing tickets.
     */
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Gets all tickets.
     *
     * @return a list of all tickets.
     */
    @GetMapping("")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    /**
     * Gets a ticket by ID.
     *
     * @param id the ID of the ticket.
     * @return the ticket with the specified ID, or null if no such ticket exists.
     */
    @GetMapping("{id}")
    public Ticket getTicketById(@PathVariable("id") Integer id) {
        return ticketService.getTicketById(id).orElse(null);
    }

    /**
     * Adds a new ticket.
     *
     * @param ticket the ticket to add.
     * @return a redirect to the tickets page.
     */
    @PostMapping("/addTicket")
    public String addTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }

    /**
     * Updates a ticket.
     *
     * @param toUpdate the ticket to update.
     * @param id       the ID of the ticket to update.
     * @return a ResponseEntity containing the updated ticket, or a not found status if no such ticket exists.
     */
    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@RequestBody Ticket toUpdate, @PathVariable("id") Integer id) {
        if (ticketService.getTicketById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        ticketService.saveTicket(toUpdate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a ticket.
     *
     * @param id the ID of the ticket to delete.
     * @return a redirect to the tickets page.
     */
    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable("id") Integer id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }
}