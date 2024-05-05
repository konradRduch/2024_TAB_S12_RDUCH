package org.skistation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"HistorySkiCard\"")
public class HistorySkiCard
{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lift_id")
    private Lift lift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ski_card_id")
    private SkiCard skiCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    public SkiCard getSkiCard() {
        return skiCard;
    }

    public void setSkiCard(SkiCard skiCard) {
        this.skiCard = skiCard;
    }

}