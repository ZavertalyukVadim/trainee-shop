package com.example.demo.dao;

import com.example.demo.entities.Goods;
import com.example.demo.entities.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
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
        try {
            logger.debug("try to get goods with id=" + id);
            return entityManager.createQuery(criteria).getSingleResult();
        } catch (Exception e) {

            logger.debug("attempt to get goods with nonexistent id = " + id);
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    @Transactional
    public Integer createGoods(Goods goods) {
        goods.setId(null);
        logger.debug("try to save goods =" + goods);
        try {

            entityManager.flush();
            entityManager.persist(goods);
            return goods.getId();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return 0;
        }
    }

    @Transactional
    public boolean updateGoods(Integer id, Goods goods) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Goods> criteria = builder.createCriteriaUpdate(Goods.class);
        Root root = criteria.from(Goods.class);

        criteria.set("name", goods.getName());
        criteria.set("price", goods.getPrice());
        criteria.set("type", goods.getType());
        criteria.set("vendor", goods.getVendor());

        criteria.where(builder.greaterThanOrEqualTo(root.get("id"), id));

        try {
            entityManager.createQuery(criteria).executeUpdate();
            logger.debug("Update goods with input id = " + id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.debug("attempt to update goods with nonexistent id = " + id);
            return false;
        }
    }

    @Transactional
    public boolean delete(Integer id) {
        logger.debug("try to delete goods with id=" + id);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaDelete<Goods> criteria = builder.
                createCriteriaDelete(Goods.class);

        Root e = criteria.from(Goods.class);

        criteria.where(builder.lessThanOrEqualTo(e.get("id"), id));
        try {

            this.entityManager.createQuery(criteria).executeUpdate();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();

            logger.debug("attempt to delete goods with nonexistent id = " + id);
            return false;
        }
    }

    @Transactional
    public List<Goods> getGoodsByTypes(List<Type> types) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery(Goods.class);
        Root<Goods> root = criteria.from(Goods.class);
        criteria.select(root);
        Expression<String> exp = root.get("type");
        Predicate parentPredicate = exp.in(types);
        criteria.where(parentPredicate);
        try {
            logger.debug("try to get goods with types=" + types);
            return entityManager.createQuery(criteria).getResultList();
        }catch (Exception e){
            logger.debug("attempt to get goods by types " + types);
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }
}
