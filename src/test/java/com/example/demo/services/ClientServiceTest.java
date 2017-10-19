//package com.example.demo.services;
//
//import com.example.demo.entities.Client;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DataJpaTest
//@EnableWebMvc
//public class ClientServiceTest {
//    @Autowired
//    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
//    private ClientService service;
//
//    private Client first, second, third;
//    private Integer id;
//    private List<Client> clientList = new ArrayList<>();
//
////    @Test(expected = RuntimeException.class)
//    public ClientServiceTest() {
//    }
//
//    public void testData(){
//        first = new Client();
//        first.setId(1);
//        first.setName("Name");
//        clientList.add(first);
//    }
//
//    @Test
//    public void getAllClients() throws Exception {
//        assertNotNull(service.getAllClients());
//    }
//
//    @Test
//    public void getAllClientsIfOnePresent() throws Exception {
//        testData();
//        Client client = new Client();
//        client.setId(1);
//        client.setName("Name");
//        service.createClient(client);
//
//        assertThat(service.getAllClients(), is(clientList));
//    }
//
//    @Test
//    public void addOneClientAndReadHim(){
//        first = new Client();
//        first.setName("client");
//
//        service.createClient(first);
//
//        assertNotNull(service.getAllClients());
//    }
//
//    @Test
//    public void addTwoClientAndSeeCount(){
//        first = new Client();
//        first.setName("client");
//        second = new Client();
//        second.setName("second");
//
//        service.createClient(first);
//        service.createClient(second);
//
//        assertThat(service.getAllClients().stream().count(),is(3L));
//    }
//
//    @Test
//    public void addClientInDbAndDeleteHim(){
//        first = new Client();
//        first.setName("client");
//
//        service.createClient(first);
//
//        assertTrue(service.deleteClientById(first.getId()));
//    }
//
//    @Test
//    public void addTwoClientInDbAndDeleteTheir(){
//        first = new Client();
//        first.setName("client");
//        second = new Client();
//        second.setName("client");
//
//        service.createClient(first);
//        service.createClient(second);
//
//        assertTrue(service.deleteClientById(first.getId()));
//        assertTrue(service.deleteClientById(second.getId()));
//    }
//
//    @Test
//    public void addClientInDbAndUpdateHim(){
//        first = new Client();
//        first.setName("client");
//        second = new Client();
//        second.setId(7);
//        second.setName("second");
//
//        service.createClient(first);
//        Client oldClient = service.getClientById(first.getId());
//        oldClient.setName("second");
//        service.updateClient(oldClient.getId(), oldClient);
//
//        assertThat(second, is(oldClient));
//    }
//}