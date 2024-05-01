package org.skistation.services;

import org.skistation.repositories.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService
{
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
