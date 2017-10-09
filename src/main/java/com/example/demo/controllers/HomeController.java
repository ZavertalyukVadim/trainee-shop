package com.example.demo.controllers;

import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {
    private final ClientService clientService;

    @Autowired
    public HomeController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/")
    public void test(){
        clientService.test();
    }
}
