package org.skistation.models;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Represents a price list entity.
 * It contains the prices for specific time periods.
 */
@Entity
public class PriceList
{
    /**
     * The ID of the price list.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The start time of the price list.
     */
    @Column(name = "time_start", nullable = false)
    private LocalDate timeStart;

    /**
     * The end time of the price list.
     */
    @Column(name = "time_end", nullable = false)
    private LocalDate timeEnd;

    /**
     * The ticket price in the price list.
     */
    @Column(name = "ticket_price", nullable = false)
    private Float ticketPrice;

    /**
     * The pass price in the price list.
     */
    @Column(name = "pass_price", nullable = false)
    private Float passPrice;

    /**
     * Constructs a new price list with the specified start time, end time, ticket price, and pass price.
     *
     * @param timeStart   the start time of the price list
     * @param timeEnd     the end time of the price list
     * @param ticketPrice the ticket price in the price list
     * @param passPrice   the pass price in the price list
     */
    public PriceList(LocalDate timeStart, LocalDate timeEnd, Float ticketPrice, Float passPrice) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.ticketPrice = ticketPrice;
        this.passPrice = passPrice;
    }

    public PriceList() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDate timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDate getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDate timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Float getPassPrice() {
        return passPrice;
    }

    public void setPassPrice(Float passPrice) {
        this.passPrice = passPrice;
    }
}