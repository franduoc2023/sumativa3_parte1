package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Envio;
import com.example.demo.service.EnvioService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


 

@RestController
@RequestMapping("/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> getEnvios() {
        return envioService.getAllEnvios();
    }

    @GetMapping("/{id}")
    public Optional<Envio> getEnvioByid(@PathVariable Long id) {
        return envioService.getEnvioByid(id);
    }

    @PostMapping
    public  Envio createEnvio(@RequestBody Envio envio){
        return envioService.createEnvio(envio);
    }
    
    @PutMapping("/{id}")
    public Envio updateEnvio(@PathVariable Long id, @RequestBody Envio envio) {        
        return envioService.updateEnvio(id, envio);
    }

    @DeleteMapping("/{id}")
    public void deleteEnvio(@PathVariable Long id) 
    
{ envioService.deleteEnvio(id);



}


@GetMapping("/{id}/origen")
public String getOrigenDelEnvio(@PathVariable Long id) {
    Optional<Envio> envio = envioService.getEnvioByid(id);

    if (!envio.isPresent()) {
        throw new RuntimeException("condicion para evitar error: " + id);
    }

 
    return envio.get().getDireccion().getDireccionDespacho();
}


}
