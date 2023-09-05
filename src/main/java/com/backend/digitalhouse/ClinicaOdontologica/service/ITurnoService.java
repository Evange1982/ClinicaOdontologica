package com.backend.digitalhouse.ClinicaOdontologica.service;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.TurnoSalidaDto;

import java.util.List;

public interface ITurnoService{
    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto registrarTurno(TurnoEntradaDto paciente);

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id);

    TurnoSalidaDto modificarTurno(TurnoEntradaDto pacienteModificado);
}
