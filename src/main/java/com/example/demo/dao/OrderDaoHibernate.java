package com.example.demo.dao;

import com.example.demo.entities.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDaoHibernate {
    private final SessionFactory sessionFactory;


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
        return session.byId(Order.class).load(id);
    }

    @Transactional(noRollbackFor = Exception.class)
    public Integer createOrder(Order order) {
        Session session = sessionFactory.openSession();
        return ((Integer) session.save(order));
    }

//    public boolean delete(Integer id) {
//        Session session = sessionFactory.openSession();
//        try {
//            session.getTransaction().begin();
////            id  =  session.createQuery("insert into Order (:name,:status,:client_id,:order_id) select o.id from Order o").executeUpdate());
//            session.save(order);
//            session.delete();
//            session.flush();
//            session.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
//                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
//                session.getTransaction().rollback();
//            }
//            return false;
//        } finally {
//            session.close();
//        }
//    }

}
