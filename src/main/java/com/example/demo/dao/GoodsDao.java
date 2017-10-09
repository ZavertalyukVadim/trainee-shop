package com.example.demo.dao;

import com.example.demo.entities.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsDao extends JpaRepository<Goods,Integer>{
}
