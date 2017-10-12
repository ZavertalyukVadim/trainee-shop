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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.utils.Calculator.priceCalculation;

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
        logger.info("attempt to get all clients");
        return clientDao.findAll();
    }

    public Client getClientById(Integer id) {
        logger.info("attempt to get client with id = " + id);
        return clientDao.findOne(id);
    }

    public Integer createClient(Client client) {
        logger.info("attempt to create client");
        try {
            return clientDao.save(client).getId();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean updateClient(Integer id, Client client) {
        logger.info("attempt to update client with id = " + id);
        Client newClient = clientDao.findOne(id);
        logger.debug("check if client with id " + id + " exists in database");
        if (newClient != null) {
            logger.debug("Update Client with input id = " + id);
            client.setId(id);
            clientDao.save(client);
            return true;
        } else {
            logger.debug("attempt to update client with nonexistent id = " + id);
            return false;
        }
    }

    public boolean deleteClientById(Integer id) {
        logger.info("attempt to delete client with id = " + id);
        try {
            clientDao.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void test1() {
        Client client = new Client("name");
        client.setDiscount(10);
        logger.debug("saved object client: " + clientDao.save(client));
        Vendor vendor = new Vendor();
        vendor.setName("vendor");
        logger.debug("saved object vendor: " + vendorDao.save(vendor));
        Goods goods = new Goods();
        goods.setPrice(BigDecimal.valueOf(1000));
        goods.setName("goods name");
        goods.setType(Type.COMPUTER);
        goods.setVendor(vendor);
        logger.debug("saved object goods with vendor: " + goodsDao.save(goods));
        Goods goods1 = new Goods();
        goods1.setPrice(BigDecimal.valueOf(1000));
        goods1.setName("goods1 name");
        goods1.setType(Type.COMPUTER);
        goods1.setVendor(vendor);
        logger.debug("saved object goods with vendor: " + goodsDao.save(goods1));

        Goods goods2 = new Goods();
        goods2.setPrice(BigDecimal.valueOf(500));
        goods2.setName("goods1 name");
        goods2.setType(Type.COMPUTER);
        goods2.setVendor(vendor);
        logger.debug("saved object goods with vendor: " + goodsDao.save(goods2));

        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods);
        goodsList.add(goods1);
        goodsList.add(goods2);
        vendor.setGoods(goodsList);
        logger.debug("saved object vendor with goods: " + vendorDao.save(vendor));
        Order order = new Order();
        order.setName("order");
        order.setOrderItems(Arrays.asList(
                new OrderItem(goods, 5),
                new OrderItem(goods1, 5),
                new OrderItem(goods2,10)
        ));
        order.setClient(
                client
        );
        List<OrderItem> list = new ArrayList<>();
        priceCalculation(list, 0);


        order.setTotalPrice(priceCalculation(order.getOrderItems(), order.getClient().getDiscount()));
        logger.debug("saved object order with orderItem and client: " + orderDao.save(order));
        client.setOrders(Arrays.asList(order));
        logger.debug("saved object client with order: " + clientDao.save(client));
        logger.info("completed insert test data");
    }


}
