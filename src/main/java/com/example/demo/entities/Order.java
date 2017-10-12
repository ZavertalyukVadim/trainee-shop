package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();
    //общяя цена
    private float totalPrice;

    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
    private Client client;

    public Order() {
    }

    public Order(String name, List<OrderItem> orderItems, Client client) {
        this.name = name;
        this.orderItems = orderItems;
        this.client = client;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderItems=" + orderItems +
                ", client=" + client.getId() +
                '}';
    }


}
