package com.example.demo.services;

import com.example.demo.dao.OrderItemDaoInJbdc;
import com.example.demo.entities.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemDaoInJbdc orderItemDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrderItemService(OrderItemDaoInJbdc orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    public List<OrderItem> getAllOrderItems() {
        logger.info("attempt to get all orderItem");
        return orderItemDao.findAll();
    }

    public OrderItem getOrderItemById(Integer id) {
        logger.info("attempt to get orderItem with id = " + id);
        return orderItemDao.findOne(id);
    }

    public Integer createOrderItem(OrderItem orderItem) {
        logger.info("attempt to create orderItem");
        try {
            return orderItemDao.create(orderItem);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean updateOrderItem(Integer id, OrderItem orderItem) {
        logger.info("attempt to update orderItem with id = " + id);
        OrderItem newOrderItem = orderItemDao.findOne(id);
        logger.debug("check if orderItem with id " + id + " exists in database");
        if (newOrderItem != null) {
            logger.debug("Update orderItem with input id = " + id);
            orderItem.setId(id);
            orderItemDao.update(orderItem);
            return true;
        } else {
            logger.debug("attempt to update orderItem with nonexistent id = " + id);
            return false;
        }
    }

    public boolean deleteOrderItemById(Integer id) {
        logger.info("attempt to delete orderItem with id = " + id);
          return   orderItemDao.delete(id);

    }
}
