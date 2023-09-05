package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Turno;
import com.backend.digitalhouse.ClinicaOdontologica.repository.TurnoRepository;
import org.apache.logging.log4j.Logger;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
        TurnoSalidaDto turnoSalidaDto;
        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());
        String pacienteNoEnBdd = "El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEnBdd = "El odontologo no se encuentra en nuestra base de datos";
        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new RuntimeException("El paciente y el odontologo no se encuentran en nuestra base de datos");
            } else if (paciente == null) {
                LOGGER.error(pacienteNoEnBdd);
                throw new RuntimeException(pacienteNoEnBdd);
            } else {
                LOGGER.error(odontologoNoEnBdd);
                throw new RuntimeException(odontologoNoEnBdd);
            }
        } else {
            Turno turnoNuevo = turnoRepository.registrar(modelMapper.map(turnoEntradaDto, Turno.class));
            turnoSalidaDto = entidadADto(turnoNuevo);
            LOGGER.info("Nuevo turno registrado con exito: {}", turnoSalidaDto);
        }
        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos(long id) {
        return null;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(long id) {
        return null;
    }

    @Override
    public void eliminarTurno(long id) {

    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        return null;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(int id) {
        return null;
    }

    @Override
    public void eliminarTurno(int id) {

    }

    private void configureMappings() {
        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> mapper.map(Turno::getPaciente, TurnoSalidaDto::setPacienteTurnoSalidaDto))
                .addMappings(mapper -> mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologoTurnoSalidaDto));
    }

    public TurnoSalidaDto entidadADto(Turno turno) {
        return modelMapper.map(turno, TurnoSalidaDto.class);
    }

}

