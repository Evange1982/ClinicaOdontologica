package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.OdontologoTurnoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Turno;
import com.backend.digitalhouse.ClinicaOdontologica.repository.TurnoRepository;
import com.backend.digitalhouse.ClinicaOdontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService ){
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnosSalida = null;
        List<Turno> turnosEntity = turnoRepository.findAll();

        if(turnosEntity != null){
            turnosSalida = turnosEntity.stream().map(this::entityToSalidaDto).toList();
            LOGGER.info("Se encontraron {} registros", turnosSalida.size());
        }else {
            LOGGER.warn("No hay turnos registrados");
        }

        return turnosSalida;
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());

        if (paciente == null && odontologo == null) {
            LOGGER.error("El paciente y el odontólogo no se encuentran en nuestra base de datos");
            return null;
        } else if (paciente == null) {
            LOGGER.error("El paciente no se encuentra en nuestra base de datos");
            return null;
        } else if (odontologo == null) {
            LOGGER.error("El odontólogo no se encuentra en nuestra base de datos");
            return null;
        }

        Turno turnoNuevo = turnoRepository.save(entradaDtoToEntity(turnoEntradaDto));
        TurnoSalidaDto turnoSalidaDto = entityToSalidaDto(turnoNuevo);
        LOGGER.info("Nuevo turno registrado con éxito: {}", turnoSalidaDto);

        return turnoSalidaDto;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoEncontrado = turnoRepository.findById(id).orElse(null);

        if(turnoEncontrado == null){
            LOGGER.error("No se encontró un turno con el ID: {}", id);
            return null;
        }

        LOGGER.info("Turno encontrado {}", turnoEncontrado);
        return entityToSalidaDto(turnoEncontrado);
    }

    @Override
    public void eliminarTurno(Long id) {

    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoEntradaDto pacienteModificado) {
        return null;
    }

    private TurnoSalidaDto entityToSalidaDto(Turno turnoSalida){
        return modelMapper.map(turnoSalida, TurnoSalidaDto.class);
    }

    private Turno entradaDtoToEntity(TurnoEntradaDto turnoEntrada){
        return modelMapper.map(turnoEntrada, Turno.class);
    }
}
