package com.example.demo.dao;

import com.example.demo.entities.Goods;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GoodsDaoHibernate {
    private final SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GoodsDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Goods> getAllGoods() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT g from Goods g").list();
    }

    @Transactional
    public Goods getGoodsById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            logger.debug("try to get goods with id=" + id);
            return session.byId(Goods.class).load(id);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Transactional
    public Integer createGoods(Goods goods) {
        Session session = sessionFactory.openSession();
        try {
            logger.debug("try to create goods");
            return ((Integer) session.save(goods));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return 0;
        }
    }

    @Transactional
    public boolean updateGoods(Integer id, Goods goods) {
        logger.trace("try to update goods with id=" + id);
        Session session = sessionFactory.getCurrentSession();
        Goods persistedGoods = session.get(Goods.class, id);
        persistedGoods.setId(id);
        persistedGoods.setName(goods.getName());
        persistedGoods.setType(goods.getType());
        persistedGoods.setVendor(goods.getVendor());
        persistedGoods.setPrice(goods.getPrice());
        try {
            session.persist(persistedGoods);
            return true;
        } catch (Exception e) {
            logger.debug("attempt to update goods with nonexistent id = " + id);
            return false;
        }
    }

    @Transactional
    public boolean delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        logger.debug("try to delete goods with id=" + id);
        try {
            session.createQuery("delete from Goods g where g.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            logger.debug("attempt to delete goods with nonexistent id = " + id);
            return false;
        }
    }
}
