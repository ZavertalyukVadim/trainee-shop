package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/{id}")
    public Order getOrderById(@PathVariable("id") Integer id){
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Integer createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PutMapping
    public Order updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteOrderById(@PathVariable("id") Integer id){
        return orderService.deleteOrderById(id);
    }
}
