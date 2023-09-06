package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Paciente;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.ClinicaOdontologica.repository.PacienteRepository;
import com.backend.digitalhouse.ClinicaOdontologica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        Paciente pacienteRecibido = dtoEntradaAEntidad(paciente);
        Paciente pacienteRegistrado = pacienteRepository.save(pacienteRecibido);
        return entidadADtoSalida(pacienteRegistrado);
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteEncontrado = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDto pacienteEncontradoDto = null;

        if(pacienteEncontrado != null){
            pacienteEncontradoDto = entidadADtoSalida(pacienteEncontrado);
            LOGGER.info("Paciente encontrado: {}", pacienteEncontradoDto);
        }else {
            LOGGER.error("El id: {} no se encuentra registrado en la base de datos", id);

        }

        return pacienteEncontradoDto;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        List<PacienteSalidaDto> listaPacientesDto = null;
        if(listaPacientes != null){
            listaPacientesDto = listaPacientes.stream().map(this::entidadADtoSalida).toList();
            LOGGER.info("Listado de todos los pacientes: {}", listaPacientesDto);
        }else {
            LOGGER.info("No hay pacientes");
        }
        return listaPacientesDto;
    }
    @Override
    public void eliminarPaciente(Long id){
        if(buscarPacientePorId(id) != null ){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id: {}", id);
        }else {
            LOGGER.error("No se ha encontrado el paciente con id {}", id);
        }

    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) {
        PacienteSalidaDto pacienteSalidaDto = null;
        Paciente pacienteAModificar = pacienteRepository.getReferenceById(pacienteModificado.getId());

        if(pacienteAModificar != null){
            pacienteAModificar = dtoModificadoAEntidad(pacienteModificado);
            pacienteSalidaDto = entidadADtoSalida(pacienteRepository.save(pacienteAModificar));
            LOGGER.warn("Se ha modificado el paciente: ", pacienteSalidaDto);
        }

        return pacienteSalidaDto;
    }

    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilio));
        modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilio, Paciente::setDomicilio));
    }

    public Paciente dtoEntradaAEntidad(PacienteEntradaDto pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

    public PacienteSalidaDto entidadADtoSalida(Paciente paciente) {
        return modelMapper.map(paciente, PacienteSalidaDto.class);
    }

    public Paciente dtoModificadoAEntidad(PacienteModificacionEntradaDto pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }
}
