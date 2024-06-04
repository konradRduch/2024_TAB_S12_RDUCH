package org.skistation.repositories;

import org.skistation.models.LiftTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Represents a lift ticket repository interface extending JpaRepository.
 * It provides methods to perform queries on the lift ticket table in the database.
 * Lift tickets table is the join table between the lift and ticket tables.
 */
@Repository
public interface LiftTicketRepository extends JpaRepository<LiftTicket, Integer>
{
    /**
     * Finds lift tickets by their lift ID.
     *
     * @param liftId the lift ID to search for
     * @return a list of lift tickets with the specified lift ID
     */
    List<LiftTicket> findByLiftId(int liftId);

    /**
     * Finds lift tickets by their ticket ID.
     *
     * @param ticketId the ticket ID to search for
     * @return a list of lift tickets with the specified ticket ID
     */
    List<LiftTicket> findByTicketId(int ticketId);
}