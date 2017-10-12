package com.example.demo.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private ClientService clientService;

    @Test
    public void getAllClients() throws Exception {
        assertThat(clientService.getAllClients().isEmpty(),is(0));
    }

}