package com.example.demo.dao;

import com.example.demo.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client,Integer>{
}
