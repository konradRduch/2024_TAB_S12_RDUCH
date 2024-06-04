package org.skistation.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents a pass entity.
 */
@Entity
public class Pass
{
    /**
     * The ID of the pass.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The active status of the pass. It is false if the pass is suspended or expired.
     */
    @Column(name = "active")
    private Boolean active;

    /**
     * The type name of the pass. It can be "day", "week", or "month".
     */
    @Column(name = "pass_type_name", length = Integer.MAX_VALUE)
    private String passTypeName;

    /**
     * The price of the pass.
     */
    @Column(name = "price")
    private Float price;

    /**
     * The order associated with the pass.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * The price list associated with the pass.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    /**
     * The start time of the pass.
     */
    @Column(name = "time_start", nullable = false)
    private LocalDateTime timeStart;

    /**
     * The end time of the pass.
     */
    @Column(name = "time_end", nullable = false)
    private LocalDateTime timeEnd;

    /**
     * The discount status of the pass.
     */
    @Column(name = "discount")
    private Boolean discount;

    /**
     * The suspension date of the pass. It is null if the pass is not suspended.
     */
    @Column(name = "suspension_date")
    private LocalDateTime suspensionDate = null;

    public Pass() {
    }

    /**
     * Constructs a new pass with the specified active status, pass type name, price, order, price list, start time, end time, and discount.
     *
     * @param active       the pass's active status
     * @param passTypeName the pass's type name ("day", "week", or "month")
     * @param price        the pass's price (in PLN)
     * @param order        the associated order
     * @param priceList    the associated price list
     * @param timeStart    the pass's start time
     * @param timeEnd      the pass's end time
     * @param discount     the pass's discount status
     */
    public Pass(Boolean active, String passTypeName, Float price, Order order, PriceList priceList, LocalDateTime timeStart, LocalDateTime timeEnd, Boolean discount) {
        this.active = active;
        this.passTypeName = passTypeName;
        this.price = price;
        this.order = order;
        this.priceList = priceList;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPassTypeName() {
        return passTypeName;
    }

    public void setPassTypeName(String passTypeName) {
        this.passTypeName = passTypeName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

    public LocalDateTime getSuspensionDate() {
        return suspensionDate;
    }

    public void setSuspensionDate(LocalDateTime suspensionDate) {
        this.suspensionDate = suspensionDate;
    }
}