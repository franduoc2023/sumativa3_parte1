package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Envio;

    @DataJpaTest
    @AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
    public class EnvioRepositoryTest{
        @Autowired
        private EnvioRepository envioRepository;
            
        @Test
        public void guardarEnvioTest(){
            Envio envio = new Envio();
            envio.setName("envio prueba");
            Envio resultado = envioRepository.save(envio);
               assertNotNull(resultado.getId());
        assertEquals("envio prueba", resultado.getName());
    
    }


        @Test
        public void incrementoIdEnvio() {
            Envio envioId = new Envio();
            envioId.setName("envio prueba cambio id 1");
            Envio envioId2 = new Envio();
            envioId2.setName("envio prueba cambio id  2");
            Envio resultadoId1 = envioRepository.save(envioId);
            Envio resultadoId2 = envioRepository.save(envioId2);
            assertNotNull(resultadoId2.getId());
            assertNotNull(resultadoId1.getId());
            assertNotEquals(resultadoId1.getId(), resultadoId2.getId());
    }
    
        }
    



    

