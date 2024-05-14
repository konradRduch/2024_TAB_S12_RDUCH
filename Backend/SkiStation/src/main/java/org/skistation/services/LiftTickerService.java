package org.skistation.services;

import org.skistation.models.Lift;
import org.skistation.models.LiftPass;
import org.skistation.models.LiftTicket;
import org.skistation.models.Ticket;
import org.skistation.repositories.LiftTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiftTickerService
{
    private final LiftTicketRepository liftTicketRepository;
    private final LiftService liftService;
    private final TicketService ticketService;

    public LiftTickerService(LiftTicketRepository liftTicketRepository, LiftService liftService, TicketService ticketService) {
        this.liftTicketRepository = liftTicketRepository;
        this.liftService = liftService;
        this.ticketService = ticketService;
    }

    public void addLiftTicket(int liftTicketId, int liftId) {
        Lift lift = liftService.getLiftById(liftId).orElseGet(null);
        Ticket ticket = ticketService.getTicketById(liftTicketId).orElseGet(null);
        if (lift == null || ticket == null) {
            return;
        }
        addLiftTicket(lift, ticket);
    }

    public void addLiftTicket(Lift lift, Ticket ticket) {
        liftTicketRepository.save(new LiftTicket(lift, ticket));
    }

    public List<LiftTicket> getLiftTicketes() {
        return liftTicketRepository.findAll();
    }

    public List<LiftTicket> getLiftTicketesByLiftId(int liftId) {
        return liftTicketRepository.findByLiftId(liftId);
    }

    public List<LiftTicket> getLiftTicketesByTicketId(int ticketId) {
        return liftTicketRepository.findByTicketId(ticketId);
    }
}
