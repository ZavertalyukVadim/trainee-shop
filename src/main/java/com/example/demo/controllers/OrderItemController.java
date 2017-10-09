package com.example.demo.controllers;

import com.example.demo.entities.OrderItem;
import com.example.demo.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private final OrderItemService orderItemService;


    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItem> getAllOrderItems(){
        return orderItemService.getAllOrderItems();
    }

    @GetMapping(value = "/{id}")
    public OrderItem getOrderItemById(@PathVariable("id") Integer id){
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping
    public Integer createOrderItem(@RequestBody OrderItem orderItem){
        return orderItemService.createOrderItem(orderItem);
    }

    @PutMapping
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem){
        return orderItemService.updateOrderItem(orderItem);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteOrderItemById(@PathVariable("id") Integer id){
        return orderItemService.deleteOrderItemById(id);
    }
}
