package com.example.demo.dao;

import com.example.demo.entities.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ClientDaoHibernate {
    private final SessionFactory sessionFactory;
    private final EntityManager entityManager;


    @Autowired
    public ClientDaoHibernate(SessionFactory sessionFactory, EntityManager entityManager) {
        this.sessionFactory = sessionFactory;
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Client> getAllClients(){
        return entityManager.createQuery("SELECT c from Client c").getResultList();
    }
}
