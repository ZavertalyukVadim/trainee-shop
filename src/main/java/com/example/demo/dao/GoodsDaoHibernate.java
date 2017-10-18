package com.example.demo.dao;

import com.example.demo.entities.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GoodsDaoHibernate {
    @PersistenceContext
    private final EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GoodsDaoHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Goods> getAllGoods() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery(Goods.class);
        Root<Goods> root = criteria.from(Goods.class);
        criteria.select(root);
        return this.entityManager.createQuery(criteria).getResultList();
    }

    @Transactional
    public Goods getGoodsById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery(Goods.class);
        Root<Goods> root = criteria.from(Goods.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), id));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }

    @Transactional
    public Integer createGoods(Goods goods) {
        goods.setId(null);
        logger.debug("try to save goods =" + goods);
        entityManager.flush();
        entityManager.persist(goods);
        return goods.getId();
    }

    @Transactional
    public boolean updateGoods(Integer id, Goods goods) {
        try {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Goods> criteria = builder.createCriteriaUpdate(Goods.class);
            Root root = criteria.from(Goods.class);
            criteria.set("name", goods.getName());
            criteria.set("price", goods.getPrice());
            criteria.set("type", goods.getType());
//        criteria.set("vendor", goods.getVendor());
            criteria.where(builder.greaterThanOrEqualTo(root.get("id"), id));
            entityManager.createQuery(criteria).executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
    }
}
