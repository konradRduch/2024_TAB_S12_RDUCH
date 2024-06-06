package org.skistation.controllers;

import org.skistation.models.Order;
import org.skistation.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing orders.
 * It is responsible for handling requests related to orders.
 */
@RestController
@RequestMapping("/orders")
public class OrderController
{
    /**
     * Service for managing orders.
     */
    private final OrderService orderService;

    /**
     * Constructs a new OrderController with the specified order service.
     *
     * @param orderService the service for managing orders.
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Gets all orders.
     *
     * @return a list of all orders.
     */
    @GetMapping("")
    public List<Order> getAllOrder() {
        return orderService.getAllOrders();
    }

    /**
     * Gets an order by ID.
     *
     * @param id the ID of the order.
     * @return the order with the specified ID.
     */
    @GetMapping("{id}")
    public Order getOrderById(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id).get();
    }

    /**
     * Adds a new order.
     *
     * @param ticket the order to add.
     * @return a redirect to the orders page.
     */
    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order ticket) {
        orderService.saveOrder(ticket);

        return "redirect:/tickets";
    }

    /**
     * Updates an order.
     *
     * @param toUpdate the order to update.
     * @param id       the ID of the order to update.
     * @return a ResponseEntity containing the updated order, or a not found status if no such order exists.
     */
    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@RequestBody Order toUpdate, @PathVariable("id") Integer id) {
        if (orderService.getOrderById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        orderService.saveOrder(toUpdate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes an order.
     *
     * @param id the ID of the order to delete.
     * @return a redirect to the orders page.
     */
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
        return "redirect:/tickets";
    }

}