package org.skistation.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PriceList
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_start", nullable = false)
    private LocalDateTime timeStart;

    @Column(name = "time_end", nullable = false)
    private LocalDateTime timeEnd;

    @Column(name = "ticket_price", nullable = false)
    private Float ticketPrice;

    @Column(name = "pass_price", nullable = false)
    private Float passPrice;

    public PriceList(LocalDateTime timeStart, LocalDateTime timeEnd, Float ticketPrice, Float passPrice) {
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