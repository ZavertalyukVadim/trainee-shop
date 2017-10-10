package com.example.demo.services;

import com.example.demo.dao.GoodsDao;
import com.example.demo.entities.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    private final GoodsDao goodsDao;

    @Autowired
    public GoodsService(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }


    public List<Goods> getAllGoods(){

        return goodsDao.findAll();
    }

    public Goods getGoodsById(Integer id){
        return goodsDao.findOne(id);
    }

    public Integer createGoods(Goods goods){
        return goodsDao.save(goods).getId();
    }

    public boolean updateGoods(Integer id,Goods goods){
        try {
            Goods newGoods = goodsDao.findOne(id);
            newGoods.setName(goods.getName());
            newGoods.setType(goods.getType());
            newGoods.setVendors(goods.getVendors());
            goodsDao.save(newGoods);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteGoodsById(Integer id){
        try {
            goodsDao.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
