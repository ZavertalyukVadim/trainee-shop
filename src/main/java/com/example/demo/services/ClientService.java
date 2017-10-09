package com.example.demo.services;

import com.example.demo.dao.ClientDao;
import com.example.demo.dao.OrderDao;
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
    private final OrderDao orderDao;


    @Autowired
    public ClientService(ClientDao clientDao,  OrderDao orderDao) {
        this.clientDao = clientDao;
        this.orderDao = orderDao;
    }

    public List<Client> getAllClients(){
        return clientDao.findAll();
    }

    public Client getClientById(Integer id){
        return clientDao.findOne(id);
    }

    public Integer createClient(Client client){
        Client newClient = clientDao.save(client);
        return newClient.getId();
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

    public void test() {

        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(5);
        orderItems.add(orderItem);


        Order newOrder = createOrder(orderItems);

        Client client = createClient(newOrder);
        System.out.println(client);
        newOrder.setClient(client);
        orderDao.save(newOrder);
    }

    private Client createClient(Order newOrder) {
        Client client = new Client();
        client.setName("Name of client");
        client.setOrder(newOrder);
        return clientDao.save(client);
    }

    private Order createOrder(Set<OrderItem> orderItems) {
        Order order = new Order();
        order.setName("name of order");
        order.setOrderItems(orderItems);
        return orderDao.save(order);
    }
}
