package org.skistation.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ticket
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount_of_rides")
    private Integer amountOfRides;

    @Column(name = "price_per_ride")
    private Float pricePerRide;

    @Column(name = "ticket_type_name", length = Integer.MAX_VALUE)
    private String ticketTypeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    @Column(name = "time_start", nullable = false)
    private LocalDateTime timeStart;

    @Column(name = "time_end", nullable = false)
    private LocalDateTime timeEnd;

    @Column(name = "discount")
    private Boolean discount;

    @Column(name = "total")
    private Float total;

    public Ticket() {
    }

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