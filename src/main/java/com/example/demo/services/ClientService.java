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

    public Client getClientById(Integer id){
        return clientDao.findOne(id);
    }

    public Integer createClient(Client client){
        return clientDao.save(client).getId();
    }

    public Client updateClient(Integer id,Client client){
        Client newClient = clientDao.findOne(id);
        newClient.setName(client.getName());
        newClient.setOrder(client.getOrder());
        return clientDao.save(newClient);
    }

    public boolean deleteClientById(Integer id){
        try {
            clientDao.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void test() {
        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(5);
        OrderItem orderItem1 = orderItemDao.save(orderItem);
        orderItems.add(orderItem1);
        Order order = new Order();
        order.setName("name of order");
        order.setOrderItems(orderItems);
        Order newOrder = orderDao.save(order);
        orderItem1.setOrder(newOrder);
        Client client = new Client();
        client.setName("Name of client");
        client.setOrder(newOrder);
        newOrder.setClient(clientDao.save(client));
        orderDao.save(newOrder);
    }
}
