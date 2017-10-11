package com.example.demo.services;

import com.example.demo.dao.ClientDao;
import com.example.demo.dao.GoodsDao;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.VendorDao;
import com.example.demo.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {
    private final ClientDao clientDao;
    private final OrderDao orderDao;
    private final VendorDao vendorDao;
    private final GoodsDao goodsDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public ClientService(ClientDao clientDao, OrderDao orderDao, VendorDao vendorDao, GoodsDao goodsDao) {
        this.clientDao = clientDao;
        this.orderDao = orderDao;
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
        return clientDao.save(client).getId();
    }

    public boolean updateClient(Integer id, Client client) {
        Client newClient = clientDao.findOne(id);
        logger.debug("check if client with id " + id + " exists in database");
        if (newClient != null) {
            logger.debug("Update Client with input id = " + id);
            client.setId(id);
            clientDao.save(client);
            return true;
        } else {
            logger.debug("attempt to update user with nonexistent id = " + id);
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
        logger.debug("saved object client: " + clientDao.save(client));
        Vendor vendor = new Vendor();
        vendor.setName("vendor");
        logger.debug("saved object vendor: " + vendorDao.save(vendor));
        Goods goods = new Goods();
        goods.setName("goods name");
        goods.setType(Type.COMPUTER);
        goods.setVendor(vendor);
        logger.debug("saved object goods with vendor: " + goodsDao.save(goods));
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods);
        vendor.setGoods(goodsList);
        logger.debug("saved object vendor with goods: " + vendorDao.save(vendor));
        Order order = new Order();
        order.setName("order");
        order.setOrderItems(Arrays.asList(
                new OrderItem(goods, 5),
                new OrderItem(5)
        ));
        order.setClient(
                client
        );
        logger.debug("saved object order with orderItem and client: " + orderDao.save(order));
        client.setOrders(Arrays.asList(order));
        logger.debug("saved object client with order: " + clientDao.save(client));
        logger.info("completed insert test data");
    }
}
