package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = orderService.getAllOrders();

        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody Order order) {
        return (orderService.createOrder(order) >= 1) ? new ResponseEntity<>(HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public void updateOrder(@PathVariable("id") Integer id, @RequestBody @Valid Order order, HttpServletResponse response,BindingResult result) {
        if (result.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if ((orderService.updateOrder(id, order))) {
            response.setStatus(HttpServletResponse.SC_RESET_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Order> deleteOrderById(@PathVariable("id") Integer id) {
        return (orderService.deleteOrderById(id)) ? new ResponseEntity<>(HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
