package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "orderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(name = "count")
    private Integer count;

    public OrderItem() {
    }

    public OrderItem(Integer count) {
        this.count = count;
    }

    public OrderItem(Goods goods, Integer count) {
        this.goods = goods;
        this.count = count;
    }

    public OrderItem(Integer id, Integer count, Goods goods) {
        this.id = id;
        this.goods = goods;
        this.count = count;
    }

    public OrderItem(Integer id, Goods goods, Integer count) {
        this.id = id;
        this.goods = goods;
        this.count = count;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", goods=" + goods +
                ", count=" + count +
                '}';
    }
}
