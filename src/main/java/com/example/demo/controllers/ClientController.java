package com.example.demo.controllers;

import com.example.demo.entities.Client;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
        if (clientService.getClientById(id) != null) {
            return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createClient(@RequestBody Client client) {
        return (clientService.createClient(client) >= 1) ? new ResponseEntity<>(HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public void updateClient(@PathVariable("id") Integer id, @RequestBody Client client, HttpServletResponse response) {
        if (clientService.updateClient(id, client)) {
            response.setStatus(HttpServletResponse.SC_RESET_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteClientById(@PathVariable("id") Integer id, HttpServletResponse response) {
        if (clientService.deleteClientById(id)) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
