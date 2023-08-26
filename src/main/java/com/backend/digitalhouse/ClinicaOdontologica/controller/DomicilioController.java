package com.backend.digitalhouse.ClinicaOdontologica.controller;

import com.backend.digitalhouse.ClinicaOdontologica.entity.Domicilio;
import com.backend.digitalhouse.ClinicaOdontologica.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    private final IDomicilioService domicilioService;

    @Autowired
    public DomicilioController(IDomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @GetMapping("/")
    public List<Domicilio> listarDomicilios(){
        return domicilioService.listarDomicilios();
    }

    @PostMapping("/registrar")
    public Domicilio registrarDomicilio(@RequestBody Domicilio domicilio){
        return domicilioService.registrarDomicilio(domicilio);
    }

    @PutMapping("/actualizar")
    public Domicilio actualizarDomicilio(@RequestBody Domicilio domicilio){
        return domicilioService.modificarDomicilio(domicilio);
    }

    @GetMapping("/{id}")
    public Domicilio obtenerDomicilioPorId(@PathVariable int id){
        return domicilioService.buscarDomicilioPorId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarDomicilio(@PathVariable int id){
        domicilioService.eliminarDomicilio(id);
    }
}
