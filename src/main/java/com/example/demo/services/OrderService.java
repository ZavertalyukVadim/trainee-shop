package com.example.demo.services;

import com.example.demo.dao.ClientDaoHibernate;
import com.example.demo.dao.GoodsDaoHibernate;
import com.example.demo.dao.OrderDaoHibernate;
import com.example.demo.dao.OrderItemDao;
import com.example.demo.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderDaoHibernate orderDaoHibernate;
    private final OrderItemDao orderItemDao;
    private final GoodsDaoHibernate goodsDao;
    private final ClientDaoHibernate clientDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrderService(OrderDaoHibernate orderDaoHibernate, OrderItemDao orderItemDao, GoodsDaoHibernate goodsDao, ClientDaoHibernate clientDao) {

        this.orderDaoHibernate = orderDaoHibernate;
        this.orderItemDao = orderItemDao;

        this.goodsDao = goodsDao;
        this.clientDao = clientDao;
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
        logger.debug("check if order with id " + id + " exists in database");
        return orderDaoHibernate.updateOrder(id, order);
    }

    public boolean deleteOrderById(Integer id) {
        logger.info("attempt to deleteOrder order with id = " + id);

        return orderDaoHibernate.deleteOrder(id);
    }

    public List<Order> searchOrder(Integer id, List<Status> statuses) {
        if (id == null) {
            return orderDaoHibernate.searchOrderByStatuses(statuses);
        }
        if (statuses == null || statuses.isEmpty()) {
            return orderDaoHibernate.searchOrderById(id);
        }
        return orderDaoHibernate.searchOrderByIdAndStatuses(id, statuses);
    }

    public boolean updateStatusInOrder(Integer id, String status) {
        Status newStatus = Status.valueOf(status);
        return orderDaoHibernate.updateStatusInOrder(id, newStatus);
    }

    public Integer createOrderFromBasket(List<Integer> listId) {
        List<Goods> goodsList = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        for (Integer id : listId) {
            goodsList.add(goodsDao.getGoodsById(id));

        }
        for (Goods goods : goodsList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(1);
            orderItem.setGoods(goods);
//            orderItemDao.save(orderItem);
            orderItems.add(orderItem);
        }
        Client client = clientDao.getClientById(1);
        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setDate(new Date());
        order.setStatus(Status.NEW);
        order.setName("my");
        order.setClient(client);
        for (OrderItem orderItem:orderItems){
            orderItem.setOrder(order);
//            orderItemDao.save(orderItem);
        }
        orderDaoHibernate.createOrder(order);
        return order.getId();
    }
}


//    id - все order независимо от статусов
//    cтатус(ы) все order независимо у всех
//        id,statuses - order клиента со статусами передаными