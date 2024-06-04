package org.skistation.models;

import jakarta.persistence.*;

/**
 * Represents a lift entity.
 */
@Entity
public class Lift
{
    /**
     * The ID of the lift.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the lift.
     */
    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    /**
     * The active status of the lift.
     */
    @Column(name = "active")
    private Boolean active;

    /**
     * The distance of the lift.
     */
    @Column(name = "distance")
    private Float distance;

    public Lift() {
    }

    /**
     * Constructs a new lift with the specified name, active status, and distance.
     *
     * @param name the lift's name
     * @param active the lift's active status
     * @param distance the lift's distance
     */
    public Lift(String name, Boolean active, Float distance) {
        this.name = name;
        this.active = active;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}