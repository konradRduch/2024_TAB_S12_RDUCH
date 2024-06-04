package org.skistation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

/**
 * Represents a client with an email and phone number.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Client
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "phone")
    private Integer phone;

    /**
     * Default constructor.
     */
    public Client() {
    }

    /**
     * Constructs a new client with the specified email and phone number.
     *
     * @param email the client's email
     * @param phone the client's phone number
     */
    public Client(String email, Integer phone) {
        this.email = email;
        this.phone = phone;
    }

    /**
     * Returns the client's ID.
     *
     * @return the client's ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the client's ID.
     *
     * @param id the new ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the client's email.
     *
     * @return the client's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the client's email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the client's phone number.
     *
     * @return the client's phone number
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     * Sets the client's phone number.
     *
     * @param phone the new phone number
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}