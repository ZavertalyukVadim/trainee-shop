package com.example.demo.controllers;

import com.example.demo.entities.Type;
import com.example.demo.services.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class HomeController {
    private final TypesService typesService;

    @Autowired
    public HomeController(TypesService typesService) {
        this.typesService = typesService;
    }

    @GetMapping(value = "/")
    public void test(){
    }

    @GetMapping(value = "/types")
    public List<Type> getAllTypes(){
        return typesService.getAllTypes();
    }
}
