package org.skistation.controllers;

import java.util.List;

import org.skistation.models.Ticket;
import org.skistation.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController
{
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping("")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("{id}")
    public Ticket getTicketById(@PathVariable("id") Integer id) {
        return ticketService.getTicketById(id).get();
    }

    @PostMapping("/addTicket")
    public String addTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);

        return "redirect:/tickets";
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@RequestBody Ticket toUpdate, @PathVariable("id") Integer id) {
        if (ticketService.getTicketById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        ticketService.saveTicket(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable("id") Integer id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }

}
