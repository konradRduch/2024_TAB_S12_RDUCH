package org.skistation.services;

import org.skistation.models.Order;
import org.skistation.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Represents an order service class.
 * It provides methods to perform operations on the order table in the database.
 */
@Service
public class OrderService
{
    /**
     * The order repository to perform operations on the order table in the database.
     */
    private final OrderRepository orderRepository;

    /**
     * Constructs a new order service with the specified order repository.
     *
     * @param orderRepository the order repository to perform operations on the order table in the database
     */
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Retrieves an order by their ID.
     *
     * @param id the ID of the order to retrieve
     * @return an Optional containing the order if found, or an empty Optional if not
     */
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    /**
     * Modifies an order in the database.
     *
     * @param order the order to modify
     * @return an Optional containing the modified order if found, or an empty Optional if not
     */
    public Optional<Order> modifyOrder(Order order) {
        Optional<Order> orderOptional = orderRepository.findById(order.getId());
        if (orderOptional.isPresent()) {
            orderRepository.save(order);
        }
        return orderOptional;
    }

    /**
     * Saves an order to the database.
     *
     * @param order the order to save
     * @return the saved order
     */
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Deletes an order from the database by their ID.
     *
     * @param id the ID of the order to delete
     */
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return a list of all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves all orders for the specified client ID from the database.
     *
     * @param clientId the ID of the client
     * @return a list of all orders for the specified client ID
     */
    public List<Order> getOrdersByClientId(Integer clientId) {
        return orderRepository.findByClientId(clientId);
    }
}