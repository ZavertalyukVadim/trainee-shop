package com.example.demo.services;

import com.example.demo.dao.*;
import com.example.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {
    private final ClientDao clientDao;
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final VendorDao vendorDao;
    private final GoodsDao goodsDao;


    @Autowired
    public ClientService(ClientDao clientDao, OrderDao orderDao, OrderItemDao orderItemDao, VendorDao vendorDao, GoodsDao goodsDao) {
        this.clientDao = clientDao;
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        this.vendorDao = vendorDao;
        this.goodsDao = goodsDao;
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

        Vendor vendor = new Vendor();
        vendor.setName("vendor");
        vendorDao.save(vendor);

        Goods goods = new Goods();
        goods.setName("goods name");
        goods.setType(Type.COMPUTER);
        goods.setVendors(vendor);
        goodsDao.save(goods);

        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods);
        vendor.setGoods(goodsList);
        vendorDao.save(vendor);

        Order order = new Order();
        order.setName("order");
        order.setOrderItems(Arrays.asList(
                new OrderItem( goods,5),
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
