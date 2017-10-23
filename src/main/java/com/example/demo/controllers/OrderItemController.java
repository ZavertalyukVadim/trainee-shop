package com.example.demo.controllers;

import com.example.demo.entities.OrderItem;
import com.example.demo.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return new ResponseEntity<>(orderItemService.getAllOrderItems(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable("id") Integer id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        if (orderItem != null) {
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrderItem(@RequestBody OrderItem orderItem) {
        Integer id = orderItemService.createOrderItem(orderItem);
        return (id != null) ? new ResponseEntity<>(id, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public void updateOrderItem(@PathVariable("id") Integer id, @RequestBody OrderItem orderItem, HttpServletResponse response) {
        if (orderItemService.updateOrderItem(id, orderItem)) {
            response.setStatus(HttpServletResponse.SC_RESET_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrderItemById(@PathVariable("id") Integer id, HttpServletResponse response) {
        if (orderItemService.deleteOrderItemById(id)) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
