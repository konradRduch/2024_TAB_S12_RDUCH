package org.skistation.services;

import org.skistation.models.Lift;
import org.skistation.models.LiftTicket;
import org.skistation.models.Ticket;
import org.skistation.repositories.LiftTicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LiftTicketService
{
    private final LiftTicketRepository liftTicketRepository;
    private final LiftService liftService;
    private final TicketService ticketService;

    public LiftTicketService(LiftTicketRepository liftTicketRepository, LiftService liftService, TicketService ticketService) {
        this.liftTicketRepository = liftTicketRepository;
        this.liftService = liftService;
        this.ticketService = ticketService;
    }

    public void addLiftTicket(int liftId, int ticketId) {
        Lift lift = liftService.getLiftById(liftId).orElseGet(null);
        Ticket ticket = ticketService.getTicketById(ticketId).orElseGet(null);
        if (lift == null || ticket == null) {
            return;
        }

        if(ticketService.bounceTicket(ticketId)){
            addLiftTicket(lift, ticket);
        }

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

    public Float getTotalTrackDistance(Integer ticketId){
        List<LiftTicket> liftTickets = getLiftTicketesByTicketId(ticketId);
        double totalDistance = liftTickets.stream()
                .mapToDouble(ticket -> ticket.getLift().getDistance()).sum();

        return (float) totalDistance;
    }

    public Integer getAmountOfRidesLeft(Integer ticketId){
        return  ticketService.getTicketById(ticketId).get().getAmountOfRides();
    }

    private boolean checkDate(Integer ticketId) {
        Optional<Ticket> ticketOpt = ticketService.getTicketById(ticketId);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime timeStart = ticket.getTimeStart();
            LocalDateTime timeEnd = timeStart.plusDays(1);
            return now.isAfter(timeStart) && now.isBefore(timeEnd);
        }
        return false;
    }
    public boolean isTicketActive(Integer ticketId){
        if(ticketService.getTicketById(ticketId).get().getAmountOfRides()>0 && checkDate(ticketId)){
            return true;
        }
        return false;
    }

    public LocalDateTime getTicketTimeStart(int ticketId) {
        return this.ticketService.getTicketById(ticketId).get().getTimeStart();
    }

    public LocalDateTime getTicketTimeEnd(int ticketId) {
        return this.ticketService.getTicketById(ticketId).get().getTimeEnd();
    }
}
