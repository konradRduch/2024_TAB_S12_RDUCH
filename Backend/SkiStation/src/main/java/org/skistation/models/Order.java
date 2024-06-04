package org.skistation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents an order entity.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "\"Order\"")
public class Order
{
    /**
     * The ID of the order.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The total price of the order. It is the sum of the prices of all the passes and tickets in the order.
     */
    @Column(name = "total")
    private Float total;

    /**
     * The client associated with the order.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * The date of the order.
     */
    @Column(name = "order_date")
    private LocalDateTime orderDate = LocalDateTime.now();

    public Order() {
    }

    /**
     * Constructs a new order with the specified total price and associated client.
     *
     * @param total  the order's total price
     * @param client the associated client
     */
    public Order(Float total, Client client) {
        this.total = total;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}