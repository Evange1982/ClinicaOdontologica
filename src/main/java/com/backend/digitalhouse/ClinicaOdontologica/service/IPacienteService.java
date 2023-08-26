package com.backend.digitalhouse.ClinicaOdontologica.service;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;

import java.util.List;

public interface IPacienteService {
    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    PacienteSalidaDto buscarPacientePorId(int id);

    void eliminarPaciente(int id);

    PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado);


}