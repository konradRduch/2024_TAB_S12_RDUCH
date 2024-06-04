package org.skistation.models;

import jakarta.persistence.*;

/**
 * Represents a lift pass entity.
 * It is a many-to-many relationship between lifts and passes.
 * It is used to store the history of pass and lift usage.
 */
@Entity
@Table(name = "lift_pass")
public class LiftPass
{
    /**
     * The ID of the lift pass.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * The lift associated with the lift pass.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lift_id")
    private Lift lift;

    /**
     * The pass associated with the lift pass.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pass_id")
    private Pass pass;

    public LiftPass() {
    }

    /**
     * Constructs a new lift pass with the specified lift and pass.
     *
     * @param lift the lift associated with the lift pass
     * @param pass the pass associated with the lift pass
     */
    public LiftPass(Lift lift, Pass pass) {
        this.lift = lift;
        this.pass = pass;
    }

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

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }
}