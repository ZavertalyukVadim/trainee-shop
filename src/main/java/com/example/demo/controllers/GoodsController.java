package com.example.demo.controllers;

import com.example.demo.entities.Goods;
import com.example.demo.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<List<Goods>> getAllGoods(){
        return new ResponseEntity<>(goodsService.getAllGoods(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Goods >getGoodsById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(goodsService.getGoodsById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer >createGoods(@RequestBody Goods goods){
        return (goodsService.createGoods(goods)>=1)? new ResponseEntity<>(HttpStatus.CREATED) :
        new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public void updateGoods(@PathVariable("id") Integer id,@RequestBody Goods goods,HttpServletResponse response){
        if (goodsService.updateGoods(id,goods)){
            response.setStatus(HttpServletResponse.SC_RESET_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGoodsById(@PathVariable("id") Integer id,HttpServletResponse response){
        if (goodsService.deleteGoodsById(id)){
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
