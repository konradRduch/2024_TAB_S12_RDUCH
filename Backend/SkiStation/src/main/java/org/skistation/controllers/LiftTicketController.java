package org.skistation.controllers;

import org.skistation.models.DTO.BounceTicketRequest;
import org.skistation.models.DTO.TicketSummary;
import org.skistation.services.LiftTicketService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controller for managing lift tickets.
 * It is responsible for handling requests related to lifts and tickets history.
 */
@RestController
@RequestMapping("/liftTicket")
public class LiftTicketController
{
    /**
     * Service for managing lift tickets.
     */
    private final LiftTicketService liftTicketService;

    /**
     * Constructs a new LiftTicketController with the specified lift ticket service.
     *
     * @param liftTicketService the service for managing lift tickets.
     */
    public LiftTicketController(LiftTicketService liftTicketService) {
        this.liftTicketService = liftTicketService;
    }

    /**
     * Adds a new lift ticket.
     *
     * @param request the request to add a lift ticket.
     * @return a redirect to the lift tickets page.
     */
    @PostMapping("/bounceliftTicket")
    public String addLiftTicket(@RequestBody BounceTicketRequest request) {
        boolean active = liftTicketService.isTicketActive(request.getTicketId());
        if (active) {
            liftTicketService.addLiftTicket(request.getLiftId(), request.getTicketId());
        }
        return "redirect:/liftTicket";
    }

    /**
     * Gets a summary of a ticket.
     *
     * @param liftId   the ID of the lift.
     * @param ticketId the ID of the ticket.
     * @return a summary of the ticket.
     */
    @GetMapping("/summary")
    public TicketSummary getSummary(@RequestParam int liftId, @RequestParam int ticketId) {
        Integer amountOfRidesLeft = liftTicketService.getAmountOfRidesLeft(ticketId);
        Boolean active = liftTicketService.isTicketActive(ticketId);
        Float distance = liftTicketService.getTotalTrackDistance(ticketId);
        LocalDateTime timeStart = liftTicketService.getTicketTimeStart(ticketId);
        LocalDateTime timeEnd = liftTicketService.getTicketTimeEnd(ticketId);

        return new TicketSummary(active, distance, amountOfRidesLeft, timeStart, timeEnd);
    }
}