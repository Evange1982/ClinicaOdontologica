package com.backend.digitalhouse.ClinicaOdontologica.service;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService{
    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto registrarTurno(TurnoEntradaDto paciente) throws BadRequestException;

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto modificarTurno(TurnoEntradaDto pacienteModificado);
}
