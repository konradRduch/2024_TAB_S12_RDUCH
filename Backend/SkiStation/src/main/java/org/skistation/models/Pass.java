package org.skistation.models;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Pass
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "pass_type_name", length = Integer.MAX_VALUE)
    private String passTypeName;

    @Column(name = "price")
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    @Column(name = "time_start", nullable = false)
    private LocalDateTime timeStart;

    @Column(name = "time_end", nullable = false)
    private LocalDateTime timeEnd;

    @Column(name = "discount")
    private Boolean discount;

    @Column(name = "suspension_date")
    private LocalDateTime suspensionDate = null;

    public Pass() {
    }


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

    public Boolean getActive() {
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