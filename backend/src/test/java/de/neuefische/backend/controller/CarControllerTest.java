package de.neuefische.backend.controller;

import de.neuefische.backend.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser(username = "user", password = "123")
    void getAllCars() throws Exception {
        //GIVEN

        //WHEN&THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    @WithMockUser(username = "user", password = "123")
    void saveCar() throws Exception {
        //GIVEN
        Car car = Car.builder()
                .id("1")
                .brand("BMW")
                .model("3")
                .build();
        //WHEN&THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                        "brand": "BMW",
                        "model": "3"
                        }
                        """)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "brand": "BMW",
                        "model": "3"
                        }
                        """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "user", password = "123")
    void getCarById_ShouldReturn404_WhenIdIsInvalid() throws Exception {
        //GIVEN

        //WHEN&THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/cars/6464"))
                .andExpect(status().isNotFound());
    }

    }