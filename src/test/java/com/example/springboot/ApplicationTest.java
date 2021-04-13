package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSearchAndDelete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=pineapples")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=pineapples")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("pineapples"))));
    }

    @Test
    void testCaseSensitivity() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=strawberries")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=Strawberries").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("has been deleted"))));
    }
}