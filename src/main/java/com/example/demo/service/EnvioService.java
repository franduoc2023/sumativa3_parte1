package com.example.demo.service;

import com.example.demo.model.Envio;
import java.util.List;
import java.util.Optional;

public interface EnvioService {
    List<Envio> getAllEnvios();
    Optional<Envio> getEnvioByid(Long id);
    Envio createEnvio(Envio envio);
    Envio updateEnvio(Long id, Envio envio);
    void deleteEnvio(Long id);
}