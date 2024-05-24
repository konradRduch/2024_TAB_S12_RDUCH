package org.skistation.controllers;

import org.skistation.models.DTO.BounceTicketRequest;
import org.skistation.models.DTO.TicketSummary;
import org.skistation.services.LiftTicketService;
import org.skistation.services.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/liftTicket")
public class LiftTicketController
{
    private final LiftTicketService liftTicketService;

    public LiftTicketController(LiftTicketService liftTicketService) {
        this.liftTicketService = liftTicketService;
    }

    @PostMapping("/bounceliftTicket")
    public String addLiftTicket(@RequestBody BounceTicketRequest request) {
        boolean active = liftTicketService.isTicketActive(request.getTicketId());
        if(active) {
            liftTicketService.addLiftTicket(request.getLiftId(), request.getTicketId());
        }
        return "redirect:/liftTicket";
    }

    @GetMapping("/summary")
    public TicketSummary getSummary(@RequestParam int liftId, @RequestParam int ticketId) {
        Integer amountOfRidesLeft = liftTicketService.getAmountOfRidesLeft(ticketId);
        Boolean active = liftTicketService.isTicketActive(ticketId);
        Float distance = liftTicketService.getTotalTrackDistance(ticketId);
        return new TicketSummary(active, distance, amountOfRidesLeft);
    }
}
