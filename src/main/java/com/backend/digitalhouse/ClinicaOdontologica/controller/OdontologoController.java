package com.backend.digitalhouse.ClinicaOdontologica.controller;


import com.backend.digitalhouse.ClinicaOdontologica.entity.Odontologo;
import com.backend.digitalhouse.ClinicaOdontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/odontologos")
public class OdontologoController {

     private final IOdontologoService OdontologoService;

    @Autowired
    public OdontologoController(IOdontologoService OdontologoService) {
        this.OdontologoService = OdontologoService;
    }

    @GetMapping("/")
    public List<Odontologo> listarOdontologo(){
        return OdontologoService.listarOdontologos();
    }

    @PostMapping("/registrar")
    public Odontologo registrarOdontologo(@RequestBody Odontologo Odontologo){
        return OdontologoService.registrarOdontologo(Odontologo);
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologoPorId(@PathVariable int id){
        return OdontologoService.buscarOdontologoPorId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo(@PathVariable int id){OdontologoService.eliminarOdontologo(id);
    }
}


