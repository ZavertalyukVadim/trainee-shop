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
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
        client.setDiscount(10);
        client.setId(1);
        Vendor vendor = new Vendor();
        vendor.setName("vendor");
        vendor.setId(1);
        Goods goods = new Goods();
        goods.setPrice(BigDecimal.valueOf(1000));
        goods.setName("goods name");
        goods.setType(Type.COMPUTER);
        goods.setVendor(vendor);
        goods.setId(1);
        Goods goods1 = new Goods();
        goods1.setPrice(BigDecimal.valueOf(1000));
        goods1.setName("goods1 name");
        goods1.setType(Type.COMPUTER);
        goods1.setVendor(vendor);
        goods1.setId(2);

        Goods goods2 = new Goods();
        goods2.setPrice(BigDecimal.valueOf(500));
        goods2.setName("goods1 name");
        goods2.setType(Type.COMPUTER);
        goods2.setVendor(vendor);
        goods2.setId(3);

        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods);
        goodsList.add(goods1);
        goodsList.add(goods2);
        vendor.setGoods(goodsList);


        Order order2 = new Order();
        order2.setName("order2");
        order2.setStatus(Status.DELIVERED);
        order2.setOrderItems(Arrays.asList(
                new OrderItem(1, goods, 5),
                new OrderItem(2, goods1, 5),
                new OrderItem(3, goods2, 10)
        ));
        order2.setClient(
                client
        );
        order2.setId(1);

        Order order1 = new Order();
        order1.setName("order1");
        order1.setStatus(Status.NEW);
        order1.setOrderItems(Arrays.asList(
                new OrderItem(4, goods, 5),
                new OrderItem(5, goods1, 5),
                new OrderItem(6, goods2, 10)
        ));
        order1.setClient(
                client
        );
        order1.setId(2);

        Order order = new Order();
        order.setName("order");
        order.setStatus(Status.ACCEPTED);
        order.setOrderItems(Arrays.asList(
                new OrderItem(7, goods, 5),
                new OrderItem(8, goods1, 5),
                new OrderItem(9, goods2, 10)
        ));
        order.setClient(
                client
        );
         order.setId(3);
        client.setOrders(Arrays.asList(order2, order1, order));
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
        Client client1 = mapper.readValue(mvcResult.getResponse().getContentAsString(), Client.class);
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

    @Test
    public void updateClient() throws Exception {
        Json json = new Json("[ " + clientService.getClientById(1).toString() + " ]");
        MvcResult mvcResult = this.mockMvc.perform(put("/client/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .content(json.toString())
                .content("application/json;charset=UTF-8"))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), is(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE));
    }

}

