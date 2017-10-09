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

    public List<OrderItem> getAllOrderItems(){
        return orderItemDao.findAll();
    }

    public OrderItem getOrderItemById(Integer id){
        return orderItemDao.findOne(id);
    }

    public Integer createOrderItem(OrderItem orderItem){
        return orderItemDao.save(orderItem).getId();
    }

    public OrderItem updateOrderItem(OrderItem orderItem){
        OrderItem newOrderItem = orderItemDao.findOne(orderItem.getId());
        newOrderItem.setOrder(orderItem.getOrder());
        newOrderItem.setCount(orderItem.getCount());
        return orderItemDao.save(newOrderItem);
    }

    public boolean deleteOrderItemById(Integer id){
        try {
            orderItemDao.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
