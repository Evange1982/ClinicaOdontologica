package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dao.IDao;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.domicilio.DomicilioEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.DomicilioModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.domicilio.DomicilioSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Domicilio;
import com.backend.digitalhouse.ClinicaOdontologica.service.IDomicilioService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class DomicilioService implements IDomicilioService {

    private final IDao<Domicilio> domicilioIDao;
    private final ModelMapper modelMapper;

    public DomicilioService(IDao<Domicilio> domicilioIDao, ModelMapper modelMapper){
        this.domicilioIDao = domicilioIDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DomicilioSalidaDto> listarDomicilios() {
        return domicilioIDao.listarTodos().stream().map(this::entityToDto).toList();
    }

    @Override
    public DomicilioSalidaDto registrarDomicilio(DomicilioEntradaDto domicilioEntrada) {
        Domicilio domicilioRegistrado = domicilioIDao.registrar(dtoToEntity(domicilioEntrada));
        return entityToDto(domicilioRegistrado);
    }

    @Override
    public DomicilioSalidaDto buscarDomicilioPorId(int id) {
        return entityToDto(domicilioIDao.buscarPorId(id));
    }

    @Override
    public DomicilioSalidaDto modificarDomicilio(DomicilioModificacionEntradaDto domicilioEntrada) {
        Domicilio domicilioSalida = domicilioIDao.modificar(domicilioModificadoEntradaDtoToEntity(domicilioEntrada));
        return entityToDto(domicilioSalida);
    }

    @Override
    public void eliminarDomicilio(int id) {
        domicilioIDao.eliminar(id);
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
