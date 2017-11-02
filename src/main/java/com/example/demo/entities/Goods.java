package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Type type;

    private BigDecimal price;

    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    public Goods() {
    }

    public Goods(Integer id, String name, BigDecimal price, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Goods(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Goods(String name, Type type, BigDecimal price, Vendor vendor) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.vendor = vendor;
    }

    public Goods(String name, BigDecimal price, Type type) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Goods(Integer id, String name, BigDecimal price, Type type, Vendor vendor) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.vendor = vendor;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        if (this.vendor != null) {
            return "Goods{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", type=" + type +
                    ", vendor= {...}" +
                    '}';
        } else return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", vendor= {}" +
                '}';
    }
}
