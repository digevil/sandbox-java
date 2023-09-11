package org.digevil.sandbox.s.boot3.simple.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TimeControllerIntegrationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetCurrent() {
        RequestBuilder request = null;

        // 构造请求
        request = get("/time/current");

        // 执行请求
        try {
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string(notNullValue()))
                    .andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetConvert() {
        RequestBuilder request = null;

        // 构造请求
        // FIXME
        request = get("/time/convert/1982-16-01 08:00:01");

        // 执行请求
        try {
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string(notNullValue()))
                    .andExpect(jsonPath("$.time", notNullValue()))
                    .andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}