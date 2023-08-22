package com.backend.digitalhouse.controller;

import com.backend.digitalhouse.entity.Paciente;
import com.backend.digitalhouse.service.IPacienteService;
import org.springframework.web.bind.annotation.*;


// http://localhost:8080/pacientes
@RestController
@RequestMapping("/pacientes")
public class Pacientecontroller {
    private final IPacienteService pacienteService;

    public Pacientecontroller(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("prueba")
    public String prueba(){
        return "Hola mundo";
    }
    //POST
    @PostMapping("registrar")
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.registrarPaciente(paciente);
    }

    //PUT
    @PutMapping("actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente pacienteModificado){
        return pacienteService.modificarPaciente(pacienteModificado);
    }
}
