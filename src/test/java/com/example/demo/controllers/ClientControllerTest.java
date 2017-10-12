package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DataJpaTest
@WebAppConfiguration
public class ClientControllerTest {

    private MockMvc mockMvc;

    //    @MockBean
    @Autowired
    private ClientService clientService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Autowired
    private WebApplicationContext wac;

    public Client test() {
        Client client = new Client("name");

        client.setId(1);
        Vendor vendor = new Vendor();
        vendor.setName("vendor");

        vendor.setId(1);
        Goods goods = new Goods();
        goods.setName("goods name");
        goods.setType(Type.COMPUTER);
        goods.setVendor(vendor);
        goods.setId(1);
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods);

        vendor.setGoods(goodsList);


        Order order = new Order();
        order.setName("order");
        order.setOrderItems(Arrays.asList(
                new OrderItem(1,goods, 5),
                new OrderItem(2,null,5)
        ));
        order.setClient(
                client
        );
        order.setId(1);
        client.setOrders(Arrays.asList(order));
        return client;
    }


    @Test
    public void getClientById() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Client client = test();


        MvcResult mvcResult = this.mockMvc.perform(get("/client/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .content("application/json;charset=UTF-8"))
                .andReturn();
        Client client1 = mapper.readValue(mvcResult.getResponse().getContentAsString(),Client.class);
        assertThat(client1.toString(), is(client.toString()));
    }

    @Test
    public void getClientByNonexistentId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/client/1000")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .content("application/json;charset=UTF-8"))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), is(HttpServletResponse.SC_NOT_FOUND));

    }

    @Test
    public void deleteClientById() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(delete("/client/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .content("application/json;charset=UTF-8"))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), is(HttpServletResponse.SC_ACCEPTED));

    }

    @Test
    public void deleteClientByNonexistentId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(delete("/client/100000")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .content("application/json;charset=UTF-8"))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), is(HttpServletResponse.SC_NOT_FOUND));

    }



}

