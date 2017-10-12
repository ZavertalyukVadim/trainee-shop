package com.example.demo.controllers;

import com.example.demo.entities.Client;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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


    @Test
    public void getClientById() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Client client = clientService.getClientById(1);

        MvcResult mvcResult = this.mockMvc.perform(get("/client/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .content("application/json;charset=UTF-8"))
                .andReturn();

        assertThat(mapper.readValue(mvcResult.getResponse().getContentAsString(),Client.class).getId(), is(client.getId()));
    }

    @Test
    public void getClientByNonexistentId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/client/1000")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .content("application/json;charset=UTF-8"))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), is(HttpServletResponse.SC_NOT_FOUND));

    }

}

