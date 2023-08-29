package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.domicilio.DomicilioEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.DomicilioModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.domicilio.DomicilioSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Domicilio;
import com.backend.digitalhouse.ClinicaOdontologica.repository.DomicilioRepository;
import com.backend.digitalhouse.ClinicaOdontologica.service.IDomicilioService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DomicilioService implements IDomicilioService {

    //private final IDao<Domicilio> domicilioIDao;
    private final ModelMapper modelMapper;
    private final DomicilioRepository domicilioRepository;

    public DomicilioService(ModelMapper modelMapper, DomicilioRepository domicilioRepository){
        this.modelMapper = modelMapper;
        this.domicilioRepository = domicilioRepository;
    }

    @Override
    public List<DomicilioSalidaDto> listarDomicilios() {
        return domicilioRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public DomicilioSalidaDto registrarDomicilio(DomicilioEntradaDto domicilioEntrada) {
        Domicilio domicilioRegistrado = domicilioRepository.save(dtoToEntity(domicilioEntrada));
        return entityToDto(domicilioRegistrado);
    }

    @Override
    public DomicilioSalidaDto buscarDomicilioPorId(Long id) {
        return entityToDto(domicilioRepository.getReferenceById(id));
    }

    @Override
    public DomicilioSalidaDto modificarDomicilio(DomicilioModificacionEntradaDto domicilioEntrada) {
        Domicilio domicilioSalida = domicilioRepository.save(domicilioModificadoEntradaDtoToEntity(domicilioEntrada));
        return entityToDto(domicilioSalida);
    }

    @Override
    public void eliminarDomicilio(Long id) {
        domicilioRepository.deleteById(id);
    }

    private Domicilio dtoToEntity(DomicilioEntradaDto domicilioEntrada){
        return  modelMapper.map(domicilioEntrada, Domicilio.class);
    }

    private DomicilioSalidaDto entityToDto(Domicilio domicilioSalida){
        return modelMapper.map(domicilioSalida, DomicilioSalidaDto.class);
    }

    private Domicilio domicilioModificadoEntradaDtoToEntity(DomicilioModificacionEntradaDto domicilioModificado){
        return modelMapper.map(domicilioModificado, Domicilio.class);
    }
}
