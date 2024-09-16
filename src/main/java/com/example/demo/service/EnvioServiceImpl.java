package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Envio;
import com.example.demo.model.Producto;
import com.example.demo.repository.EnvioRepository;
import com.example.demo.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private ProductoRepository productoRepository; 
    @Override
    public List<Envio> getAllEnvios() {
        return envioRepository.findAll();
    }

    @Override
    public Optional<Envio> getEnvioByid(Long id) {
        return envioRepository.findById(id);
    }

    @Override
    public Envio createEnvio(Envio envio) {
        Envio savedEnvio = envioRepository.save(envio);
        if (envio.getProductos() != null) {
            for (Producto producto : envio.getProductos()) {
                producto.setEnvio(savedEnvio);  
                productoRepository.save(producto);  
            }
        }

        return savedEnvio;
    }

    @Override
    public Envio updateEnvio(Long id, Envio envio) {
        if (envioRepository.existsById(id)) {
            envio.setId(id);
            Envio updatedEnvio = envioRepository.save(envio);

            if (envio.getProductos() != null) {
                for (Producto producto : envio.getProductos()) {
                    producto.setEnvio(updatedEnvio);  
                    productoRepository.save(producto);  
                }
            }

            return updatedEnvio;
        } else {
            return null;
        }
    }

    @Override
    public void deleteEnvio(Long id) {
        envioRepository.deleteById(id);
    }
}










    

