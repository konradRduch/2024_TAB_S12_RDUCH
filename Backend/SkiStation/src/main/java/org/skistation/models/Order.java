package org.skistation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Order\"")
public class Order
{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "total")
    private Float total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}