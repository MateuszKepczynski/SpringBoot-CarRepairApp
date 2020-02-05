package com.springapp.carrepairshop.carrepairshop.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PageSecurityTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowLoginPageForm() throws Exception
    {
        mockMvc.perform(get("/")).andExpect(status().isFound()).andExpect(redirectedUrl("http://localhost/showLoginPage"));
    }
}
