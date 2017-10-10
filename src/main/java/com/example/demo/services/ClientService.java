package com.example.demo.services;

import com.example.demo.dao.ClientDao;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.OrderItemDao;
import com.example.demo.entities.Client;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {
    private final ClientDao clientDao;
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;


    @Autowired
    public ClientService(ClientDao clientDao, OrderDao orderDao, OrderItemDao orderItemDao) {
        this.clientDao = clientDao;
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        test1();
    }

    public List<Client> getAllClients() {
        return clientDao.findAll();
    }

    public Client getClientById(Integer id) {
        return clientDao.findOne(id);
    }

    public Integer createClient(Client client) {
        Client newClient = clientDao.save(client);
        return newClient.getId();
    }

    public Client updateClient(Integer id, Client client) {
        Client newClient = clientDao.findOne(id);
        newClient.setName(client.getName());
        newClient.setOrder(client.getOrder());
        return clientDao.save(newClient);
    }

    public boolean deleteClientById(Integer id) {
        try {
            clientDao.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void test() {

//        List<OrderItem> orderItems = new ArrayList<>();
//        OrderItem orderItem = new OrderItem();
//        orderItem.setCount(5);
//        orderItemDao.save(orderItem);
//        orderItems.add(orderItem);
//
//        Order order = new Order();
//        order.setOrderItems(orderItems);
//        order.setName("order name");
//
//        orderDao.save(order);
//        for (OrderItem order1 : orderItems) {
//            order.setOrder(order1);
//            orderItemDao.save(order1);
//        }

//        orderItem.setOrder(order1);

//        Client client =new Client();
//        client.setName("client");
//        client.setOrder(order1);
//        clientDao.save(client);
    }

    private void getaVoid(Order newOrder, Client client) {
        newOrder.setClient(client);
        orderDao.save(newOrder);
    }

    private Client createClient() {
        Client client = new Client();
        client.setName("Name of client");
        return clientDao.save(client);
    }

    private void test1() {
        Client client = new Client("name");
        clientDao.save(client);
        Order order = new Order();
        order.setName("order");
        order.setOrderItems(Arrays.asList(
                new OrderItem(order, 5),
                new OrderItem(order, 5)
        ));
        order.setClient(
                client
        );
        orderDao.save(order);
        client.setOrder(order);
        clientDao.save(client);
    }


}
