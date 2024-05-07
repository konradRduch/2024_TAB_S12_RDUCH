package org.skistation.models;

import jakarta.persistence.*;

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

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private SkiCard skiCard;

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

}