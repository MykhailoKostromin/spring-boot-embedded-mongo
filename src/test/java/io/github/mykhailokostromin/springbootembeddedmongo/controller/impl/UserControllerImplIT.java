package io.github.mykhailokostromin.springbootembeddedmongo.controller.impl;

import io.github.mykhailokostromin.springbootembeddedmongo.ApplicationIT;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerImplIT extends ApplicationIT {

    @Test
    public void getUsersTest() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[2].name", is("Mira")));
    }

    @Test
    public void getUsersWithNameTest() throws Exception {
        mockMvc.perform(get("/users?name=Wayne"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is("Wayne")));
    }
}