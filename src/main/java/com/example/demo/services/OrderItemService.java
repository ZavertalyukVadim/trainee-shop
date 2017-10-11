package com.example.demo.services;

import com.example.demo.dao.OrderItemDao;
import com.example.demo.entities.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemDao orderItemDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrderItemService(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemDao.findAll();
    }

    public OrderItem getOrderItemById(Integer id) {
        return orderItemDao.findOne(id);
    }

    public Integer createOrderItem(OrderItem orderItem) {
        return orderItemDao.save(orderItem).getId();
    }

    public boolean updateOrderItem(Integer id, OrderItem orderItem) {
        OrderItem newOrderItem = orderItemDao.findOne(id);
        logger.debug("check if orderItem with id " + id + " exists in database");
        if (newOrderItem != null) {
            logger.debug("Update orderItem with input id = " + id);
            orderItem.setId(id);
            orderItemDao.save(orderItem);
            return true;
        } else {
            logger.debug("attempt to update orderItem with nonexistent id = " + id);
            return false;
        }

    }

    public boolean deleteOrderItemById(Integer id) {
        try {
            orderItemDao.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
