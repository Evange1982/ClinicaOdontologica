package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
<<<<<<< HEAD
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.OdontologoTurnoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Turno;
import com.backend.digitalhouse.ClinicaOdontologica.repository.TurnoRepository;
import com.backend.digitalhouse.ClinicaOdontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
=======
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Turno;
import com.backend.digitalhouse.ClinicaOdontologica.repository.TurnoRepository;
import org.apache.logging.log4j.Logger;

import org.modelmapper.ModelMapper;
>>>>>>> 6b4e9dae659d77f4def0d943085bb9bc0f918626
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
<<<<<<< HEAD
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
=======
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
>>>>>>> 6b4e9dae659d77f4def0d943085bb9bc0f918626
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
<<<<<<< HEAD
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

=======
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
>>>>>>> 6b4e9dae659d77f4def0d943085bb9bc0f918626
        return turnoSalidaDto;
    }

    @Override
<<<<<<< HEAD
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
=======
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

>>>>>>> 6b4e9dae659d77f4def0d943085bb9bc0f918626
