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
        try {
            Order newOrder = orderDao.findOne(id);
            newOrder.setClient(order.getClient());
            orderDao.save(newOrder);
            return true;
        }
        catch (Exception e){
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
