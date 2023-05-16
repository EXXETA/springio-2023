package com.exxeta.projectmatcher.controller;

import com.exxeta.projectmatcher.persistence.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EmployeeControllerIntegrationTests {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    EmployeeRepository employeeRepository;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_return_list_of_recommended_employees() throws Exception {
        mockMvc
            .perform(
                post("/employee/recommendation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"skills\":[\"Kotlin\"]}")
                    .characterEncoding("utf-8")
            )
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().json(
                    "[{\"name\":\"Alice\"},{\"name\":\"Bob\"},{\"name\":\"Carol\"}]"
            ));
    }
}
