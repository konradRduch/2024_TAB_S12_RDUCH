package org.skistation.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents a ticket entity.
 */
@Entity
public class Ticket
{
    /**
     * The ID of the ticket.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The amount of rides for the ticket.
     */
    @Column(name = "amount_of_rides")
    private Integer amountOfRides;

    /**
     * The price per ride for the ticket.
     */
    @Column(name = "price_per_ride")
    private Float pricePerRide;

    /**
     * The ticket type name.
     */
    @Column(name = "ticket_type_name", length = Integer.MAX_VALUE)
    private String ticketTypeName;

    /**
     * The order associated with the ticket.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * The price list associated with the ticket.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    /**
     * The start time of the ticket.
     */
    @Column(name = "time_start", nullable = false)
    private LocalDateTime timeStart;

    /**
     * The end time of the ticket.
     */
    @Column(name = "time_end", nullable = false)
    private LocalDateTime timeEnd;

    /**
     * The discount status of the ticket.
     */
    @Column(name = "discount")
    private Boolean discount;

    /**
     * The total price of the ticket.
     */
    @Column(name = "total")
    private Float total;

    public Ticket() {
    }

    /**
     * Constructs a new ticket with the specified amount of rides, price per ride, ticket type name, order, price list, start time, end time, discount, and total.
     *
     * @param amountOfRides  the amount of rides for the ticket
     * @param pricePerRide   the price per ride for the ticket
     * @param ticketTypeName the ticket type name
     * @param order          the order associated with the ticket
     * @param priceList      the price list associated with the ticket
     * @param timeStart      the start time of the ticket
     * @param timeEnd        the end time of the ticket
     * @param discount       the discount status of the ticket
     * @param total          the total price of the ticket
     */
    public Ticket(Integer amountOfRides, Float pricePerRide, String ticketTypeName, Order order, PriceList priceList, LocalDateTime timeStart, LocalDateTime timeEnd, Boolean discount, Float total) {
        this.amountOfRides = amountOfRides;
        this.pricePerRide = pricePerRide;
        this.ticketTypeName = ticketTypeName;
        this.order = order;
        this.priceList = priceList;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.discount = discount;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmountOfRides() {
        return amountOfRides;
    }

    public void setAmountOfRides(Integer amountOfRides) {
        this.amountOfRides = amountOfRides;
    }

    public Float getPricePerRide() {
        return pricePerRide;
    }

    public void setPricePerRide(Float pricePerRide) {
        this.pricePerRide = pricePerRide;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}