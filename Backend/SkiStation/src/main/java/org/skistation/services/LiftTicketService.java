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

/**
 * Represents a lift ticket service class.
 * It provides methods to perform operations on the lift ticket table in the database.
 */
@Service
public class LiftTicketService
{
    /**
     * The lift ticket repository to perform operations on the lift ticket table in the database.
     */
    private final LiftTicketRepository liftTicketRepository;

    /**
     * The lift service to perform operations on the lift table in the database.
     */
    private final LiftService liftService;

    /**
     * The ticket service to perform operations on the ticket table in the database.
     */
    private final TicketService ticketService;

    /**
     * Constructs a new lift ticket service with the specified lift ticket repository, lift service, and ticket service.
     *
     * @param liftTicketRepository the lift ticket repository to perform operations on the lift ticket table in the database
     * @param liftService          the lift service to perform operations on the lift table in the database
     * @param ticketService        the ticket service to perform operations on the ticket table in the database
     */
    public LiftTicketService(LiftTicketRepository liftTicketRepository, LiftService liftService, TicketService ticketService) {
        this.liftTicketRepository = liftTicketRepository;
        this.liftService = liftService;
        this.ticketService = ticketService;
    }

    /**
     * Adds a lift ticket with the specified lift ID and ticket ID.
     *
     * @param liftId   the ID of the lift
     * @param ticketId the ID of the ticket
     */
    public void addLiftTicket(int liftId, int ticketId) {
        Lift lift = liftService.getLiftById(liftId).orElseGet(null);
        Ticket ticket = ticketService.getTicketById(ticketId).orElseGet(null);
        if (lift == null || ticket == null) {
            return;
        }

        if (ticketService.bounceTicket(ticketId)) {
            addLiftTicket(lift, ticket);
        }

    }

    /**
     * Adds a lift ticket with the specified lift and ticket.
     *
     * @param lift   the lift
     * @param ticket the ticket
     */
    public void addLiftTicket(Lift lift, Ticket ticket) {
        liftTicketRepository.save(new LiftTicket(lift, ticket));
    }

    /**
     * Retrieves all lift tickets from the database.
     *
     * @return a list of all lift tickets
     */
    public List<LiftTicket> getLiftTicketes() {
        return liftTicketRepository.findAll();
    }

    /**
     * Retrieves all lift tickets by the specified lift ID from the database.
     *
     * @param liftId the ID of the lift
     * @return a list of all lift tickets by the specified lift ID
     */
    public List<LiftTicket> getLiftTicketesByLiftId(int liftId) {
        return liftTicketRepository.findByLiftId(liftId);
    }

    /**
     * Retrieves all lift tickets by the specified ticket ID from the database.
     *
     * @param ticketId the ID of the ticket
     * @return a list of all lift tickets by the specified ticket ID
     */
    public List<LiftTicket> getLiftTicketesByTicketId(int ticketId) {
        return liftTicketRepository.findByTicketId(ticketId);
    }

    /**
     * Retrieves the total track distance by the specified ticket ID.
     *
     * @param ticketId the ID of the ticket
     * @return the total track distance
     */
    public Float getTotalTrackDistance(Integer ticketId) {
        List<LiftTicket> liftTickets = getLiftTicketesByTicketId(ticketId);
        double totalDistance = liftTickets.stream()
                .mapToDouble(ticket -> ticket.getLift().getDistance()).sum();

        return (float) totalDistance;
    }

    /**
     * Retrieves the amount of rides left by the specified ticket ID.
     *
     * @param ticketId the ID of the ticket
     * @return the amount of rides left
     */
    public Integer getAmountOfRidesLeft(Integer ticketId) {
        return ticketService.getTicketById(ticketId).get().getAmountOfRides();
    }

    /**
     * Checks the date by the specified ticket ID.
     *
     * @param ticketId the ID of the ticket
     * @return true if the date is valid, false otherwise
     */
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

    /**
     * Checks if the ticket with the specified ticket ID is active.
     *
     * @param ticketId the ID of the ticket
     * @return true if the ticket is active, false otherwise
     */
    public boolean isTicketActive(Integer ticketId) {
        if (ticketService.getTicketById(ticketId).get().getAmountOfRides() > 0 && checkDate(ticketId)) {
            return true;
        }
        return false;
    }

    /**
     * Retrieves the ticket start date and time by the specified ticket ID.
     *
     * @param ticketId the ID of the ticket
     * @return the ticket start date and time
     */
    public LocalDateTime getTicketTimeStart(int ticketId) {
        return this.ticketService.getTicketById(ticketId).get().getTimeStart();
    }

    /**
     * Retrieves the ticket end date and time by the specified ticket ID.
     *
     * @param ticketId the ID of the ticket
     * @return the ticket end date and time
     */
    public LocalDateTime getTicketTimeEnd(int ticketId) {
        return this.ticketService.getTicketById(ticketId).get().getTimeEnd();
    }
}