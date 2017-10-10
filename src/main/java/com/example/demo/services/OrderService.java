package com.example.demo.services;

import com.example.demo.dao.OrderDao;
import com.example.demo.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderDao.findOne(id);
    }

    public Integer createOrder(Order order) {
        try {
            return orderDao.save(order).getId();
        } catch (Exception e) {
            return 0;
        }

    }

    @Transactional
    public boolean updateOrder(Integer id, Order order) {
        try {
            Order order1 = orderDao.findOne(id);
            order1.setId(id);
            order1.setName(order.getName());
            order1.setOrderItems(order.getOrderItems());
            order1.setClient(order.getClient());
            orderDao.save(order1);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteOrderById(Integer id) {
        try {
            orderDao.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
