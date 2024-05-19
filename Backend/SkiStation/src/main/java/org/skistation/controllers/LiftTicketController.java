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

    @PostMapping("/bounceTicket")
    public String addLiftTicket(@RequestBody BounceTicketRequest request) {
        liftTicketService.addLiftTicket(request.getLiftId(), request.getTicketId());
        return "redirect:/liftTicket";
    }

    @GetMapping("/summary")
    public TicketSummary getSummary(@RequestBody BounceTicketRequest request) {
        Boolean active = liftTicketService.isTicketActive(request.getTicketId());
        Float distance = liftTicketService.getTotalTrackDistance(request.getLiftId(), request.getTicketId());
        TicketSummary ticketSummary = new TicketSummary(active, distance);
        return ticketSummary;
    }
}
