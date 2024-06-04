package org.skistation.models;

import jakarta.persistence.*;

/**
 * Represents a ski station worker entity with a phone number, email, and role.
 */
@Entity
public class Worker
{
    /**
     * The ID of the worker.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The phone number of the worker.
     */
    @Column(name = "phone", nullable = false)
    private Integer phone;

    /**
     * The email of the worker.
     */
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    /**
     * The role of the worker.
     */
    @Column(name = "role", nullable = false, length = Integer.MAX_VALUE)
    private String role;

    public Worker() {
    }

    /**
     * Constructs a new worker with the specified phone number, email, and role.
     *
     * @param phone the phone number of the worker
     * @param email the email of the worker
     * @param role  the role of the worker
     */
    public Worker(Integer phone, String email, String role) {
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}