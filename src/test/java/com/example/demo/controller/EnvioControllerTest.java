package com.example.demo.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.Envio;
 import com.example.demo.service.EnvioServiceImpl;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

 import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
       
@WebMvcTest(EnvioController.class)
public class EnvioControllerTest{
@Autowired
private MockMvc mockMvc;

@MockBean
private EnvioServiceImpl envioServicioMock;

@Test 
public void obtenerTodosTest () throws Exception {
    Envio envio1 = new Envio();
    envio1.setName("envio prueba 1");
    envio1.setId(1L);
    
    Envio envio2 = new Envio();
    envio2.setName("envio prueba 2");
    envio2.setId(2L);
    
    List<Envio> envios = Arrays.asList(envio1, envio2);
    when(envioServicioMock.getAllEnvios()).thenReturn(envios);
    
    mockMvc.perform(MockMvcRequestBuilders.get("/envios"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        // Ajustamos el path para acceder a la lista dentro de _embedded.envioList
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.envioList", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.envioList[0].name", Matchers.is("envio prueba 1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.envioList[1].name", Matchers.is("envio prueba 2")));
}}





