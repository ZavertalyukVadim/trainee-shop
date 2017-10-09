package com.example.demo.services;

import com.example.demo.dao.ClientDao;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.OrderItemDao;
import com.example.demo.entities.Client;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientService {
    private final ClientDao clientDao;
    private final OrderItemDao orderItemDao;
    private final OrderDao orderDao;


    @Autowired
    public ClientService(ClientDao clientDao, OrderItemDao orderItemDao, OrderDao orderDao) {
        this.clientDao = clientDao;
        this.orderItemDao = orderItemDao;
        this.orderDao = orderDao;
    }

    public List<Client> getAllClients(){
        if(clientDao.findAll().isEmpty()){
            test();
        }
        return clientDao.findAll();
    }

    private void test() {
        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(5);
        orderItems.add(orderItemDao.save(orderItem));
        Order order = new Order();
        order.setName("name of order");
        order.setOrderItems(orderItems);
        Order newOrder = orderDao.save(order);
        Client client = new Client();
        client.setName("Name of client");
        client.setOrder(newOrder);
        clientDao.save(client);
    }


}
