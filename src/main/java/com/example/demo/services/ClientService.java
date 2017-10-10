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

    public boolean updateClient(Integer id, Client client) {
        try {
            Client newClient = clientDao.findOne(id);
            newClient.setId(id);
            newClient.setName(client.getName());
            newClient.setOrder(client.getOrder());
            clientDao.save(newClient);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteClientById(Integer id) {
        try {
            clientDao.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void test1() {
        Client client = new Client("name");
        clientDao.save(client);
        Order order = new Order();
        order.setName("order");
        order.setOrderItems(Arrays.asList(
                new OrderItem( 5),
                new OrderItem( 5)
        ));
        order.setClient(
                client
        );
        orderDao.save(order);
        client.setOrder(order);
        clientDao.save(client);


    }


}
