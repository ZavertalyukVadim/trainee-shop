package com.example.demo.services;

import com.example.demo.dao.OrderDao;
import com.example.demo.entities.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderDao orderDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public boolean updateOrder(Integer id, Order order) {
        Order order1 = orderDao.findOne(id);
        logger.debug("check if order with id " + id + " exists in database");
        if (order1 != null) {
            logger.debug("Update order with input id = " + id);
            order.setId(id);
            orderDao.save(order);
            return true;
        } else {
            logger.debug("attempt to update order with nonexistent id = " + id);
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
