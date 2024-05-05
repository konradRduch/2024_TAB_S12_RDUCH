package org.skistation.services;

import org.skistation.models.Order;
import org.skistation.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService
{
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    Optional<Order> modifyOrder(Order order) {
        Optional<Order> orderOptional = orderRepository.findById(order.getId());
        if (orderOptional.isPresent()) {
            orderRepository.save(order);
        }
        return orderOptional;
    }

    Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    List<Order> getOrdersByClientId(Integer clientId) {
        return orderRepository.findByClientId(clientId);
    }
}
