package com.example.demo.services;

import com.example.demo.dao.ClientDaoHibernate;
import com.example.demo.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {
    private final ClientDaoHibernate clientDaoHibernate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public ClientService(ClientDaoHibernate clientDaoHibernate) {

//        test1();
        this.clientDaoHibernate = clientDaoHibernate;
    }

    private void test2() {
        Client client = new Client();
        client.setName("name");
    }

    public List<Client> getAllClients() {
        logger.info("attempt to get all clients");
        return clientDaoHibernate.getAllClients();
    }

    public Client getClientById(Integer id) {
        logger.info("attempt to get client with id = " + id);
        return clientDaoHibernate.getClientById(id);
    }

    public Integer createClient(Client client) {
        logger.info("attempt to create client");
        return clientDaoHibernate.createClient(client);
    }

    public boolean updateClient(Integer id, Client client) {
        logger.info("attempt to update client with id = " + id);
        return clientDaoHibernate.updateClient(id, client);
    }

    public boolean deleteClientById(Integer id) {
        logger.info("attempt to delete client with id = " + id);
        return clientDaoHibernate.deleteClientById(id);
    }

    private void test1() {
        Client client = new Client("name");
        client.setDiscount(10);
//        logger.debug("saved object client: " + clientDao.save(client));
        Vendor vendor = new Vendor();
        vendor.setName("vendor");
//        logger.debug("saved object vendor: " + vendorDao.save(vendor));
        Goods goods = new Goods();
        goods.setPrice(BigDecimal.valueOf(1000));
        goods.setName("goods name");
        goods.setType(Type.COMPUTER);
        goods.setVendor(vendor);
//        logger.debug("saved object goods with vendor: " + goodsDao.save(goods));
        Goods goods1 = new Goods();
        goods1.setPrice(BigDecimal.valueOf(1000));
        goods1.setName("goods1 name");
        goods1.setType(Type.COMPUTER);
        goods1.setVendor(vendor);
//        logger.debug("saved object goods with vendor: " + goodsDao.save(goods1));

        Goods goods2 = new Goods();
        goods2.setPrice(BigDecimal.valueOf(500));
        goods2.setName("goods1 name");
        goods2.setType(Type.COMPUTER);
        goods2.setVendor(vendor);
//        logger.debug("saved object goods with vendor: " + goodsDao.save(goods2));

        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods);
        goodsList.add(goods1);
        goodsList.add(goods2);
        vendor.setGoods(goodsList);
//        logger.debug("saved object vendor with goods: " + vendorDao.save(vendor));

        Order order2 = new Order();
        order2.setName("order2");
        order2.setStatus(Status.DELIVERED);
        order2.setOrderItems(Arrays.asList(
                new OrderItem(goods, 5),
                new OrderItem(goods1, 5),
                new OrderItem(goods2, 10)
        ));
        order2.setClient(
                client
        );
//        orderDao.save(order2);

        Order order1 = new Order();
        order1.setName("order1");
        order1.setStatus(Status.NEW);
        order1.setOrderItems(Arrays.asList(
                new OrderItem(goods, 5),
                new OrderItem(goods1, 5),
                new OrderItem(goods2, 10)
        ));
        order1.setClient(
                client
        );
//        orderDao.save(order1);

        Order order = new Order();
        order.setName("order");
        order.setStatus(Status.ACCEPTED);
        order.setOrderItems(Arrays.asList(
                new OrderItem(goods, 5),
                new OrderItem(goods1, 5),
                new OrderItem(goods2, 10)
        ));
        order.setClient(
                client
        );
//        logger.debug("saved object order with orderItem and client: " + orderDao.save(order));
        client.setOrders(Arrays.asList(order, order1, order2));
//        logger.debug("saved object client with order: " + clientDao.save(client));
        logger.info("completed insert test data");
    }


}
