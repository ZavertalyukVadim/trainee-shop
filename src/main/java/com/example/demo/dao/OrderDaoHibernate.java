package com.example.demo.dao;

import com.example.demo.entities.Order;
import com.example.demo.entities.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDaoHibernate {

    private final SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrderDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Order> getAllOrders() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT o from Order o").list();
    }

    @Transactional
    public Order getOneById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            logger.debug("try to get order with id=" + id);
            return session.byId(Order.class).load(id);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Transactional(noRollbackFor = Exception.class)
    public Integer createOrder(Order order) {
        Session session = sessionFactory.openSession();
        try {
            logger.debug("try to create order");
            return ((Integer) session.save(order));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return 0;
        }
    }

    @Transactional
    public boolean deleteOrder(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        logger.debug("try to delete order with id=" + id);
        try {
            session.createQuery("delete from Order o where o.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            logger.debug("attempt to delete order with nonexistent id = " + id);
            return false;
        }

    }

    @Transactional
    public boolean persistOrder(Integer id, Order order) {
        Session session = sessionFactory.getCurrentSession();
        Order persistedOrder = session.get(Order.class, id);
        persistedOrder.setId(id);
        persistedOrder.setName(order.getName());
        persistedOrder.setStatus(order.getStatus());
        persistedOrder.setClient(order.getClient());
        persistedOrder.setOrderItems(order.getOrderItems());
        try {
            session.persist(persistedOrder);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateOrder(Integer id, Order order) {
        Session session = sessionFactory.openSession();
        try {
            session.createQuery("update Order set name = :name, status = :status, client = :client, orderItems = :orderItems where id = :id")
                    .setParameter("id", id)
                    .setParameter("name", order.getName())
                    .setParameter("status", order.getStatus())
                    .setParameter("client", order.getClient())
//                    .setParameterList("orderItems", order.getOrderItems())
                    .executeUpdate();

            logger.debug("Update order with input id = " + id);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("attempt to update order with nonexistent id = " + id);
            return false;
        }
    }

    @Transactional
    public List<Order> searchOrderByStatuses(List<Status> statuses) {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT o from Order o where o.status in :statuses ")
                .setParameterList("statuses", statuses)
                .list();
    }

    @Transactional
    public List<Order> searchOrderById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT o from Order o where o.id = :id")
                .setParameter("id", id)
                .list();
    }

    @Transactional
    public List<Order> searchOrderByIdAndStatuses(Integer id, List<Status> statuses) {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT o from Order o  inner join o.client client where client.id = :id and o.status in :statuses ")
                .setParameter("id", id)
                .setParameterList("statuses", statuses)
                .list();
    }
}
