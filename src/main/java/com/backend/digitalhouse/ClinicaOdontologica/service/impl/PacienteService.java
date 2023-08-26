package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dao.IDao;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Paciente;
import com.backend.digitalhouse.ClinicaOdontologica.service.IPacienteService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final IDao<Paciente> pacienteIDao;
    private final ModelMapper modelMapper;

    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        Paciente pacienteRecibido = dtoEntradaAEntidad(paciente);
        Paciente pacienteRegistrado = pacienteIDao.registrar(pacienteRecibido);
        System.out.println("Se registro el paciente: "+pacienteRegistrado);
        return entidadADtoSalida(pacienteRegistrado);
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(int id) {
        Paciente pacienteEncontrado = pacienteIDao.buscarPorId(id);
        return entidadADtoSalida(pacienteEncontrado);
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        List<Paciente> pacientes = pacienteIDao.listarTodos();

        return pacientes.stream().map(this::entidadADtoSalida).toList();
    }
    @Override
    public void eliminarPaciente(int id){
        pacienteIDao.eliminar(id);
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) {
        PacienteSalidaDto pacienteSalidaDto = null;
        Paciente pacienteAModificar = pacienteIDao.buscarPorId(pacienteModificado.getId());

        if(pacienteAModificar != null){
            pacienteAModificar = dtoModificadoAEntidad(pacienteModificado);
            pacienteSalidaDto = entidadADtoSalida(pacienteIDao.modificar(pacienteAModificar));

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
