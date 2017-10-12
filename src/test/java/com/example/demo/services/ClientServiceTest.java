package com.example.demo.services;

import com.example.demo.entities.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ClientServiceTest {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private ClientService service;

    private Client first, second, third;
    private Integer id;
    private List<Client> clientList = new ArrayList<>();

    public ClientServiceTest() {
    }

    private void flushCategories() {

    }

    @Test
    public void getAllClients() throws Exception {
        assertThat(service.getAllClients(),is(clientList));
    }

}