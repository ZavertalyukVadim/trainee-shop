package com.example.demo.dao;

import com.example.demo.entities.Order;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
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
        Criteria criteria = sessionFactory.openSession().createCriteria(Order.class);
        return criteria.list();
    }
}
