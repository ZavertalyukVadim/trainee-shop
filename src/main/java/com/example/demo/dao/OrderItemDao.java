package com.example.demo.dao;

import com.example.demo.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDao extends JpaRepository<OrderItem,Integer>{
}
