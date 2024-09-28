package com.example.demo.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Envio;
import com.example.demo.repository.EnvioRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
 
@ExtendWith(MockitoExtension.class)
public class EnvioServiceImplTest {
    @InjectMocks
    private EnvioServiceImpl envioServiceImpl;
    @Mock
    private EnvioRepository envioRepositorioMock;

    @Test 
    public void guardarEnvioTest(){
        Envio envio = new Envio();{
              envio.setName("rey");
        when(envioRepositorioMock.save(any(Envio.class))).thenReturn(envio);
        Envio resultado = envioServiceImpl.createEnvio(envio);
        assertEquals("rey", resultado.getName());

        }



    }

}
