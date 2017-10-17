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
    //посмотреть тип данных дял цены

    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "goods_vendors", joinColumns = {
            @JoinColumn(name = "goods_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "vendor_id",
                    nullable = false)})
    private Vendor vendor;

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
        if (this.vendor != null){
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", vendor= {...}" +
                '}';}
        else return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", vendor= {}" +
                '}';
    }
}
