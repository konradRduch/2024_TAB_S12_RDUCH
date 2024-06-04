package org.skistation.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents a ski schedule entity.
 * It contains the schedule for a specific lift for a specific day.
 */
@Entity(name = "ski_schedule")
public class SkiSchedule
{
    /**
     * The ID of the ski schedule.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The opening time of the ski schedule.
     */
    @Column(name = "open")
    private LocalDateTime open;

    /**
     * The closing time of the ski schedule.
     */
    @Column(name = "close")
    private LocalDateTime close;

    /**
     * The lift associated with the ski schedule.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lift_id")
    private Lift lift;

    public SkiSchedule() {
    }

    /**
     * Constructs a new ski schedule with the specified opening time, closing time, and associated lift.
     *
     * @param open  the opening time of the ski schedule
     * @param close the closing time of the ski schedule
     * @param lift  the lift associated with the ski schedule
     */
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