package com.example.demo.entities;

import javax.persistence.*;

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "order_orderItems", joinColumns = {
            @JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "orderItems_id")})
    private Order order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
