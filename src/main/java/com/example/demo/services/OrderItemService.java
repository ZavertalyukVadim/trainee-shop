package com.example.demo.services;

import com.example.demo.dao.OrderItemDao;
import com.example.demo.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemDao orderItemDao;

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
        try {

            OrderItem newOrderItem = orderItemDao.findOne(id);
            newOrderItem.setId(id);
            newOrderItem.setGoods(orderItem.getGoods());
            newOrderItem.setCount(orderItem.getCount());
            orderItemDao.save(newOrderItem);
            return true;
        } catch (Exception e) {
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
