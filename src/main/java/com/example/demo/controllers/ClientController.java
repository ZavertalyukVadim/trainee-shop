package com.example.demo.controllers;

import com.example.demo.entities.Client;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping(value = "/{id}")
    public Client getClientById(@PathVariable("id") Integer id){
        return clientService.getClientById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @PutMapping(value = "/{id}")
    public Client updateClient(@PathVariable("id") Integer id,@RequestBody Client client){
        return clientService.updateClient(id,client);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteClientById(@PathVariable("id") Integer id){
        return clientService.deleteClientById(id);
    }

}
