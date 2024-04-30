package org.skistation.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "\"PriceList\"")
public class PriceList
{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "time_start", nullable = false)
    private LocalDate timeStart;

    @Column(name = "time_end", nullable = false)
    private LocalDate timeEnd;

    @Column(name = "ticket_price", nullable = false)
    private Float ticketPrice;

    @Column(name = "pass_price", nullable = false)
    private Float passPrice;

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