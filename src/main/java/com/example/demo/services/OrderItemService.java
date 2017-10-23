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
        logger.trace("attempt to get all orderItem");
        return orderItemDao.getAllGoods();
    }

    public OrderItem getOrderItemById(Integer id) {
        logger.trace("attempt to get orderItem with id = " + id);
        return orderItemDao.getGoodsById(id);
    }

    public Integer createOrderItem(OrderItem orderItem) {
        logger.info("attempt to create orderItem");
        try {
            return orderItemDao.createGoods(orderItem);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean updateOrderItem(Integer id, OrderItem orderItem) {
        return orderItemDao.updateGoods(id, orderItem);
    }

    public boolean deleteOrderItemById(Integer id) {
        logger.info("attempt to delete orderItem with id = " + id);
        try {
            orderItemDao.deleteGoods(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
