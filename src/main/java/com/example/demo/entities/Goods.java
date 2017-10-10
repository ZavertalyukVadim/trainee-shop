package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "goods_vendors", joinColumns = {
            @JoinColumn(name = "goods_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "vendor_id",
                    nullable = false)})
    private Vendor vendors;

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

    public Vendor getVendors() {
        return vendors;
    }

    public void setVendors(Vendor vendors) {
        this.vendors = vendors;
    }
}
