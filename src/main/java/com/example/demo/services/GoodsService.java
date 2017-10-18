package com.example.demo.services;

import com.example.demo.dao.GoodsDaoHibernate;
import com.example.demo.entities.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    private final GoodsDaoHibernate goodsDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GoodsService(GoodsDaoHibernate goodsDao) {
        this.goodsDao = goodsDao;
    }


    public List<Goods> getAllGoods() {
        logger.info("attempt to get all goods");
        return goodsDao.getAllGoods();
    }

    public Goods getGoodsById(Integer id) {
        logger.info("attempt to get goods with id = " + id);
        try {
            return goodsDao.getGoodsById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Integer createGoods(Goods goods) {
        logger.info("attempt to create goods");
        try {
            return goodsDao.createGoods(goods);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean updateGoods(Integer id, Goods goods) {
        logger.info("attempt to update goods with id = " + id);
        return goodsDao.updateGoods(id, goods);
    }

    public boolean deleteGoodsById(Integer id) {
        logger.info("attempt to delete goods with id = " + id);
        return goodsDao.delete(id);
    }
}
