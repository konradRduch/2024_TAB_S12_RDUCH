package org.skistation.models;

import jakarta.persistence.*;

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

    public Pass() {
    }

    public Pass(Boolean active, String passTypeName, Float price, Order order, PriceList priceList, SkiCard skiCard) {
        this.active = active;
        this.passTypeName = passTypeName;
        this.price = price;
        this.order = order;
        this.priceList = priceList;
        this.skiCard = skiCard;
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

}