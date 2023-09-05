package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Turno;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto);

    List<TurnoSalidaDto> listarTurnos(long id);

    TurnoSalidaDto buscarTurnoPorId(long id);

    void eliminarTurno(long id);

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(int id);

    void eliminarTurno(int id);

    TurnoSalidaDto entidadADto(Turno turno);
}

