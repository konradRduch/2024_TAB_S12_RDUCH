package org.skistation.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class SkiCard
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Ticket ticket;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Pass pass;

    @Column(name = "time_start", nullable = false)
    private LocalDateTime timeStart;

    @Column(name = "time_end", nullable = false)
    private LocalDateTime timeEnd;

    @Column(name = "discount")
    private Boolean discount;

    public SkiCard() {
    }

    public SkiCard(Ticket ticket, Pass pass, LocalDateTime timeStart, LocalDateTime timeEnd, Boolean discount) {
        this.ticket = ticket;
        this.pass = pass;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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

}