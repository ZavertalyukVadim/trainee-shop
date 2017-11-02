package com.example.demo.dao;

import com.example.demo.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderItemDaoInJbdc {
    final private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderItemDaoInJbdc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderItem> findAll() {
        return null;
    }

    public OrderItem findOne(Integer id) {
        return null;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public boolean update(OrderItem orderItem) {
        return false;
    }

    public Integer create(OrderItem orderItem) {
        return null;
    }
}
