package com.example.demo.services;

import com.example.demo.dao.OrderDao;
import com.example.demo.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> getAllOrders(){

        return orderDao.findAll();
    }

    public Order getOrderById(Integer id){
        return orderDao.findOne(id);
    }

    public Integer createOrder(Order order){
        return orderDao.save(order).getId();
    }

    public Order updateOrder(Order order){
        Order newOrder = orderDao.findOne(order.getId());
        newOrder.setClient(order.getClient());
        newOrder.setName(order.getName());
        newOrder.setOrderItems(order.getOrderItems());
        return orderDao.save(newOrder);
    }

    public boolean deleteOrderById(Integer id){
        try {
            orderDao.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
