package com.example.demo.dao;

import com.example.demo.entities.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GoodsDaoHibernate {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public GoodsDaoHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Goods> getAllGoods() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery( Goods.class );
        Root<Goods> root = criteria.from(Goods.class);
        criteria.select(root);
        return entityManager.createQuery(criteria).getResultList();
    }

    public Goods getGoodsById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery( Goods.class );
        Root<Goods> root = criteria.from(Goods.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), id));
        return entityManager.createQuery(criteria).getSingleResult();
    }
}
