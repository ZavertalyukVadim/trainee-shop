package com.example.demo.dao;

import com.example.demo.entities.Client;
import com.example.demo.entities.Order;
import com.example.demo.entities.Status;

import java.util.List;

public interface OrderDao {
    List<Order> findAllByClientAndStatusIn(Client client, List<Status> statuses);

    List<Order> findAllByStatusIn(List<Status> statuses);
}
