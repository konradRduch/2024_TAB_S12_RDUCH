package org.skistation.controllers;


import org.skistation.models.Order;
import org.skistation.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("")
    public List<Order> getAllOrder() {
        return orderService.getAllOrders();
    }

    @GetMapping("{id}")
    public Order getOrderById(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id).get();
    }

    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order ticket) {
        orderService.saveOrder(ticket);

        return "redirect:/tickets";
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@RequestBody Order toUpdate, @PathVariable("id") Integer id) {
        if (orderService.getOrderById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        orderService.saveOrder(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
        return "redirect:/tickets";
    }

}