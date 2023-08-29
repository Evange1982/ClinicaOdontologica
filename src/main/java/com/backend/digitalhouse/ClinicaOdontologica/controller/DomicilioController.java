package com.backend.digitalhouse.ClinicaOdontologica.controller;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.domicilio.DomicilioEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.DomicilioModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.domicilio.DomicilioSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.service.impl.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    //private final IDomicilioService domicilioService;
    private final DomicilioService domicilioService;


    @Autowired
    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DomicilioSalidaDto>> listarDomicilios(){

        return new ResponseEntity<>(domicilioService.listarDomicilios(), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<DomicilioSalidaDto> registrarDomicilio(@RequestBody DomicilioEntradaDto domicilio){
        return new ResponseEntity<>(domicilioService.registrarDomicilio(domicilio), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<DomicilioSalidaDto> actualizarDomicilio(@RequestBody DomicilioModificacionEntradaDto domicilioEntrada){
        return new ResponseEntity<>(domicilioService.modificarDomicilio(domicilioEntrada), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomicilioSalidaDto> obtenerDomicilioPorId(@PathVariable Long id){
        return new ResponseEntity<>(domicilioService.buscarDomicilioPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDomicilio(@PathVariable Long id){
        domicilioService.eliminarDomicilio(id);
        return new ResponseEntity<>("Domicilio eliminado Correctamente", HttpStatus.NO_CONTENT);
    }
}
