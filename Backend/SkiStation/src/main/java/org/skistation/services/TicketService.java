package org.skistation.services;

import org.skistation.models.Ticket;
import org.skistation.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService
{
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Optional<Ticket> getTicketById(Integer id) {
        return ticketRepository.findById(id);
    }

    public Optional<Ticket> modifyTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getId());
        if (ticketOptional.isPresent()) {
            ticketRepository.save(ticket);
        }
        return ticketOptional;
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByOrderId(Integer orderId) {
        return ticketRepository.findByOrderId(orderId);
    }

    public List<Ticket> getTicketsByPriceListId(Integer priceListId) {
        return ticketRepository.findByPriceListId(priceListId);
    }

    public boolean bounceTicket(Integer ticketId){
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
