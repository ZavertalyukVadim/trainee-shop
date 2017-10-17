package com.example.demo.dao;

import com.example.demo.entities.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderDaoHibernate {
    private final SessionFactory sessionFactory;


    @Autowired
    public OrderDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Order> getAllOrders() {
        List<Order> orders = null;
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            orders = session.createQuery("SELECT o from Order o").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return orders;
//        session.
//        return criteria.list();
    }

    public Order getOneById(Integer id) {
        Session session = sessionFactory.openSession();
        Order order = null;
        try {
            session.getTransaction().begin();
            order = ((Order) session.createQuery("SELECT o from Order o").uniqueResult());
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return order;
    }
}
