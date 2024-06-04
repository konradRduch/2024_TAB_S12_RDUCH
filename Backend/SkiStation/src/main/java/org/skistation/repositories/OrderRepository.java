package org.skistation.repositories;

import org.skistation.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Represents an order repository interface extending JpaRepository.
 * It provides methods to perform queries on the order table in the database.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>
{
    /**
     * Finds orders by their client ID.
     *
     * @param userId the client ID to search for
     * @return a list of orders with the specified client ID
     */
    List<Order> findByClientId(int userId);
}