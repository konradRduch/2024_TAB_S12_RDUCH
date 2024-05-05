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

    Optional<Ticket> getTicketById(Integer id) {
        return ticketRepository.findById(id);
    }

    Optional<Ticket> modifyTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getId());
        if (ticketOptional.isPresent()) {
            ticketRepository.save(ticket);
        }
        return ticketOptional;
    }

    Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }

    List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    List<Ticket> getTicketsByOrderId(Integer orderId) {
        return ticketRepository.findByOrderId(orderId);
    }

    List<Ticket> getTicketsByPriceListId(Integer priceListId) {
        return ticketRepository.findByPriceListId(priceListId);
    }
}
