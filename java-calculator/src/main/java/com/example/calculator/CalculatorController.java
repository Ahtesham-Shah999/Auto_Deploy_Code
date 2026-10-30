package com.example.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @GetMapping("/calculate")
    public Map<String, Object> calculate(
            @RequestParam double num1,
            @RequestParam double num2,
            @RequestParam String operation) {

        Map<String, Object> response = new HashMap<>();
        double result = 0;

        try {
            switch (operation.toLowerCase()) {
                case "add":
                    result = num1 + num2;
                    break;
                case "subtract":
                    result = num1 - num2;
                    break;
                case "multiply":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 == 0) {
                        response.put("error", "Cannot divide by zero");
                        return response;
                    }
                    result = num1 / num2;
                    break;
                default:
                    response.put("error", "Invalid operation. Use add, subtract, multiply, or divide and so on hello.");
                    return response;
            }

            response.put("result", result);

        } catch (Exception e) {
            response.put("error", "An error occurred during calculation");
        }

        return response;
    }
}
