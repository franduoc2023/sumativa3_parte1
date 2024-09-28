package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Envio;
import com.example.demo.service.EnvioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
 

@RestController
@RequestMapping("/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

 

  @GetMapping("/{id}")
public ResponseEntity<Envio> getEnvioByid(@PathVariable Long id) {
    Optional<Envio> envio = envioService.getEnvioByid(id);
    
    if (envio.isPresent()) {
        return ResponseEntity.ok(envio.get());
    } else {
        return ResponseEntity.notFound().build();
    }
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

@GetMapping
public CollectionModel<EntityModel<Envio>> getEnviosHateoas() {
    List<Envio> envios = envioService.getAllEnvios();
    
    List<EntityModel<Envio>> envioResources = envios.stream()
        .map(envio -> EntityModel.of(envio,
            linkTo(methodOn(this.getClass()).getEnvioByid(envio.getId())).withSelfRel())
        )
        .collect(Collectors.toList());

    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getEnviosHateoas());
    
    CollectionModel<EntityModel<Envio>> resources = CollectionModel.of(envioResources, linkTo.withRel("envios"));
    
    return resources;
}



}
