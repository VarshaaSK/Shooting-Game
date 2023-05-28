package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShootingController.class)
public class ShootingControllerTest {

    @MockBean
    private ShootingService shootingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldReturnHeroHealth() throws Exception {
        Mockito.when(shootingService.getCharacterHealth("hero")).thenReturn(100);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/health/hero"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(100)));
    }

    @Test
    void shouldReturnVillainHealth() throws Exception {
        Mockito.when(shootingService.getCharacterHealth("villain")).thenReturn(100);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/health/villain"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(100)));
    }

    @Test
    void shouldReduceVillainHealthOnHeroShoot() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shoot?shooter=hero"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Shoot Successful"));
    }

    @Test
    void shouldReduceHeroHealthOnVillainShoot() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shoot?shooter=villain"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Shoot Successful"));
    }

    @Test
    void shouldReduceVillainHealthBasedOnArmourState() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/armour/true"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Armour State Changed"));
    }

    @Test
    void shouldReduceVillainHealthBasedOnArmourStateAsFalse() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/armour/false"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Armour State Changed"));
    }

    @Test
    void shouldRestartTheServer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/restart"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Restarted the Server"));
    }
}
