package org.skistation.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class SkiSchedule
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "open")
    private LocalDateTime open;

    @Column(name = "close")
    private LocalDateTime close;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "lift_id")
    private Lift lift;

    public SkiSchedule() {
    }

    public SkiSchedule(LocalDateTime open, LocalDateTime close, Lift lift) {
        this.open = open;
        this.close = close;
        this.lift = lift;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOpen() {
        return open;
    }

    public void setOpen(LocalDateTime open) {
        this.open = open;
    }

    public LocalDateTime getClose() {
        return close;
    }

    public void setClose(LocalDateTime close) {
        this.close = close;
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

}