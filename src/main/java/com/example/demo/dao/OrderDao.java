package com.example.demo.dao;

import com.example.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer>{
}
