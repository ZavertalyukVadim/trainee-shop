package com.example.demo.controllers;

import com.example.demo.entities.Goods;
import com.example.demo.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
    @GetMapping
    public List<Goods> getAllGoods(){
        return goodsService.getAllGoods();
    }

    @GetMapping(value = "/{id}")
    public Goods getGoodsById(@PathVariable("id") Integer id){
        return goodsService.getGoodsById(id);
    }

    @PostMapping
    public Integer createGoods(@RequestBody Goods goods){
        return goodsService.createGoods(goods);
    }

    @PutMapping
    public Goods updateGoods(@RequestBody Goods goods){
        return goodsService.updateGoods(goods);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteGoodsById(@PathVariable("id") Integer id){
        return goodsService.deleteGoodsById(id);
    }
}
