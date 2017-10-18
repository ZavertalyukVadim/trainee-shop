package com.example.demo.dao;

import com.example.demo.entities.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClientDaoHibernate {
    @PersistenceContext
    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public ClientDaoHibernate(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Transactional
    public List<Client> getAllClients() {
        return entityManager.createQuery("SELECT c from Client c").getResultList();
    }

    @Transactional
    public Client getClientById(Integer id) {
        return entityManager.find(Client.class, id);
    }

    @Transactional
    public boolean deleteClientById(Integer id) {
        try {
            entityManager.remove(entityManager.find(Client.class, id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public void createClient(Client client) {
        client.setId(null);
        logger.debug("try to save client =" + client);
        entityManager.persist(client);
    }

    @Transactional
    public boolean updateClient(Integer id, Client client) {
        logger.debug("check if client with id " + id + " exists in database");
        Client newClient = entityManager.find(Client.class, id);
        if (newClient==null){
            return false;

        }

        entityManager.clear();
        newClient.setId(id);
        newClient.setName(client.getName());
        newClient.setDiscount(client.getDiscount());
        newClient.setOrders(client.getOrders());
        entityManager.merge(newClient);
        return true;
    }
}
