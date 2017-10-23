package com.example.demo.dao;

import com.example.demo.entities.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClientDaoHibernate {

    private final SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public ClientDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Client> getAllClients() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT c from Client c").list();
    }

    @Transactional
    public Client getClientById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            logger.trace("try to get client with id=" + id);
            return session.byId(Client.class).load(id);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean deleteClientById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        logger.trace("try to delete client with id=" + id);
        try {
            session.createQuery("delete from Client c where c.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            logger.debug("attempt to delete client with nonexistent id = " + id);
            return false;
        }
    }

    @Transactional
    public Integer createClient(Client client) {
        Session session = sessionFactory.openSession();
        client.setId(null);
        try {
            logger.trace("try to create client");
            return ((Integer) session.save(client));
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return 0;
        }
    }

    @Transactional
    public boolean updateClient(Integer id, Client client) {
        logger.trace("try to update client with id=" + id);
        Session session = sessionFactory.getCurrentSession();
        Client persistedClient = session.get(Client.class, id);
        persistedClient.setId(id);
        persistedClient.setName(client.getName());
        persistedClient.setOrders(client.getOrders());
        persistedClient.setDiscount(client.getDiscount());
        try {
            session.persist(persistedClient);
            return true;
        } catch (Exception e) {
            logger.debug("attempt to update client with nonexistent id = " + id);
            return false;
        }
    }
}
