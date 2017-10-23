package com.example.demo.dao;

import com.example.demo.entities.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderItemDao {
    private final SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrderItemDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<OrderItem> getAllGoods() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT o from OrderItem o").list();
    }

    @Transactional
    public OrderItem getGoodsById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            logger.trace("try to get orderItem with id=" + id);
            return session.byId(OrderItem.class).load(id);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Transactional
    public Integer createGoods(OrderItem orderItem) {
        Session session = sessionFactory.openSession();
        orderItem.setId(null);
        try {
            logger.debug("try to create orderItem");
            return ((Integer) session.save(orderItem));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return 0;
        }
    }

    @Transactional
    public boolean updateGoods(Integer id, OrderItem orderItem) {
        logger.trace("try to update orderItem with id=" + id);
        Session session = sessionFactory.getCurrentSession();
        OrderItem persistedOrderItem = session.get(OrderItem.class, id);
        persistedOrderItem.setId(id);
        persistedOrderItem.setGoods(orderItem.getGoods());
        persistedOrderItem.setCount(orderItem.getCount());
        try {
            session.persist(persistedOrderItem);
            return true;
        } catch (Exception e) {
            logger.debug("attempt to update orderItem with nonexistent id = " + id);
            return false;
        }
    }

    @Transactional
    public boolean deleteGoods(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        logger.trace("try to delete orderItem with id=" + id);
        try {
            session.createQuery("delete from OrderItem o where o.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            logger.debug("attempt to delete orderItem with nonexistent id = " + id);
            return false;
        }
    }
}
