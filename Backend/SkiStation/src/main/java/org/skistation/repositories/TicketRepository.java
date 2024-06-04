package org.skistation.repositories;

import org.skistation.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Represents a ticket repository interface extending JpaRepository.
 * It provides methods to perform queries on the ticket table in the database.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>
{
    /**
     * Finds tickets by their order ID.
     *
     * @param orderId the order ID to search for
     * @return a list of tickets with the specified order ID
     */
    List<Ticket> findByOrderId(Integer orderId);

    /**
     * Finds tickets by their price list ID.
     *
     * @param priceListId the price list ID to search for
     * @return a list of tickets with the specified price list ID
     */
    List<Ticket> findByPriceListId(Integer priceListId);
}