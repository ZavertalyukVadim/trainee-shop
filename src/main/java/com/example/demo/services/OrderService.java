package com.example.demo.services;

import com.example.demo.dao.OrderDaoHibernate;
import com.example.demo.entities.Order;
import com.example.demo.entities.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    //    private final OrderDao orderDao;
//    private final ClientDao clientDao;
    private final OrderDaoHibernate orderDaoHibernate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrderService(OrderDaoHibernate orderDaoHibernate) {

        this.orderDaoHibernate = orderDaoHibernate;
    }

    public List<Order> getAllOrders() {
        logger.info("attempt to get all orders");
        return orderDaoHibernate.getAllOrders();
    }

    public Order getOrderById(Integer id) {
        logger.info("attempt to get order with id = " + id);
        return orderDaoHibernate.getOneById(id);
    }

    public Integer createOrder(Order order) {
        logger.info("attempt to create order");
        return orderDaoHibernate.createOrder(order);
    }

    public boolean updateOrder(Integer id, Order order) {
        logger.info("attempt to update order with id = " + id);
//        Order order1 = orderDao.findOne(id);
        logger.debug("check if order with id " + id + " exists in database");
        return orderDaoHibernate.updateOrder(id, order);
//        if (order1 != null) {
//            logger.debug("Update order with input id = " + id);
//            order.setId(id);
//            orderDao.save(order);
//            return true;
//        } else {
//            logger.debug("attempt to update order with nonexistent id = " + id);
//            return false;
//        }
//        return false;

    }

    public boolean deleteOrderById(Integer id) {
        logger.info("attempt to deleteOrder order with id = " + id);

        return orderDaoHibernate.deleteOrder(id);
    }

    public List<Order> searchOrder(Integer id, List<Status> statuses) {
        return orderDaoHibernate.searchOrder(id, statuses);
//        Client client = clientDao.findOne(id);
//        if (client!=null){
//            return orderDao.findAllByClientAndStatusIn(client,statuses);
//        }else {
//            return orderDao.findAllByStatusIn(statuses);
//        }
//        return null;
    }
}
