package com.aathithiyan.spring_demo.controller;

import com.aathithiyan.spring_demo.dto.EmployeeResponseDTO;
import com.aathithiyan.spring_demo.service.EmployeeService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;


    @Test
    void getEmployeeById_shouldReturn200() throws Exception {

        EmployeeResponseDTO response =
                new EmployeeResponseDTO(
                        1,
                        "Aathithiyan",
                        "aathithiyan@gmail.com",
                        50000.0,
                        "IT"
                );

        when(employeeService.getEmployeeById(1))
                .thenReturn(response);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Aathithiyan"))
                .andExpect(jsonPath("$.email").value("aathithiyan@gmail.com"))
                .andExpect(jsonPath("$.salary").value(50000))
                .andExpect(jsonPath("$.department").value("IT"));
    }


    @Test
    void getEmployeeById_shouldReturn404() throws Exception {

        when(employeeService.getEmployeeById(999))
                .thenReturn(null);

        mockMvc.perform(get("/employees/999"))
                .andExpect(status().isNotFound());
    }
}