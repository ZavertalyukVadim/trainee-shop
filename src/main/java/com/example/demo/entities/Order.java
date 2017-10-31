package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
    @JsonManagedReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    //общяя цена
//    private BigDecimal totalPrice;
    private Date date;

    private Status status;

    @OneToOne
    @JoinColumn(name = "order_id")
//    @JsonManagedReference
    @JsonBackReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
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
    

//    public BigDecimal getTotalPrice() {
//        return calculateTotalPrice();
//    }
//
//    private BigDecimal calculateTotalPrice() {
//        BigDecimal sum = BigDecimal.valueOf(0);
//        BigDecimal totalPrice = null;
//        for (OrderItem orderItem : this.orderItems) {
//            if (orderItem.getGoods() != null) {
//                BigDecimal underSum = orderItem.getGoods().getPrice().multiply(BigDecimal.valueOf(orderItem.getCount()));
//                sum = sum.add(underSum);
//            }
//        }
//        if (sum != null) {
//            BigDecimal sale = BigDecimal.valueOf(this.client.getDiscount()).divide(BigDecimal.valueOf(100), 3, RoundingMode.CEILING);
//            totalPrice = sum.subtract(sum.multiply(sale));
//        }
//        return totalPrice;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", orderItems=" + orderItems +
//                ", client=" + client.getId() +
//                '}';
//    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
