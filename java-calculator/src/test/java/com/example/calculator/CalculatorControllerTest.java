package com.example.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddition() throws Exception {
        mockMvc.perform(get("/api/calculate")
                .param("num1", "5")
                .param("num2", "3")
                .param("operation", "add"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.0));
    }

    @Test
    public void testDivisionByZero() throws Exception {
        mockMvc.perform(get("/api/calculate")
                .param("num1", "5")
                .param("num2", "0")
                .param("operation", "divide"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").value("Cannot divide by zero"));
    }
}
