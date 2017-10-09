package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "venders")
public class Vender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
