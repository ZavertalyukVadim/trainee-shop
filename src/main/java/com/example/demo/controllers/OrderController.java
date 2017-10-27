package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.entities.Status;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
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

    @GetMapping(value = "/status")
    public List<Status> getStatuses() {
        return Arrays.asList(Status.ACCEPTED, Status.NEW, Status.DELIVERED);
    }

    @PutMapping(value = "/{id}/updateStatus")
    public void getStatuses(@PathVariable("id") Integer id,@RequestParam(name = "status") String status,HttpServletResponse response) {
        if ((orderService.updateStatusInOrder(id,status))) {
            response.setStatus(HttpServletResponse.SC_RESET_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody Order order) {
        Integer id = orderService.createOrder(order);
        return (id != null) ? new ResponseEntity<>(id, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Order>> searchOrder(@RequestParam(value = "id", required = false) Integer id, @RequestParam(required = false) List<Status> statuses) {
        if (id != null && id < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.searchOrder(id, statuses), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public void updateOrder(@PathVariable(value = "id") Integer id, @RequestBody @Valid Order order, HttpServletResponse response, BindingResult result) {
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
    public void deleteOrderById(@PathVariable("id") Integer id) {
        if (orderService.deleteOrderById(id)) {
            new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
