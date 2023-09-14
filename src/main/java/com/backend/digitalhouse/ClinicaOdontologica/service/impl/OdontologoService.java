package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.DomicilioModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Domicilio;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Odontologo;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.ClinicaOdontologica.repository.OdontologoRepository;
import com.backend.digitalhouse.ClinicaOdontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper){
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontologoRepository.findAll().stream().map(this::entityToDto).toList();
        LOGGER.info("Listado de todos los odontologos: {}", odontologos);
        return odontologos;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) throws BadRequestException {
        List<Odontologo> odontologoEnBd = odontologoRepository.buscarOdontologoPorMatricula(odontologo.getMatricula());
        OdontologoSalidaDto odontologoSalidaDto= null;

        if(odontologoEnBd.size() > 0){
            LOGGER.warn("Ya se encuentra registrado el Odontologo: {}", odontologo);
            throw new BadRequestException("Ya se encuentra registrado el Odontologo");
        }else{
            Odontologo odontologoEntity = odontologoRepository.save(dtoToEntity(odontologo));
            odontologoSalidaDto = entityToDto(odontologoEntity);
            LOGGER.info("Odontologo guardado: {}", odontologoSalidaDto);
        }

        return odontologoSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if(odontologo != null){
            odontologoSalidaDto = entityToDto(odontologo);
            LOGGER.info("Odontologo encontrado: {}", odontologoSalidaDto);
        }else {
            LOGGER.error("El id no se encuentra registrado en la base de datos");
        }

        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if(buscarOdontologoPorId(id) != null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        }else {
            LOGGER.error("No se ha encontrado el odontologo con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
        }
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException {
        Odontologo odontologoRecibido = modelMapper.map(odontologoModificacionEntradaDto, Odontologo.class);
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologoModificacionEntradaDto.getId()).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if (odontologoAActualizar != null) {
            odontologoAActualizar = odontologoRecibido;
            odontologoRepository.save(odontologoAActualizar);
            odontologoSalidaDto = modelMapper.map(odontologoAActualizar, OdontologoSalidaDto.class);
            LOGGER.warn("Odontologo actualizado: {}", odontologoSalidaDto);
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
        }
        return odontologoSalidaDto;
    }

    private Odontologo dtoToEntity(OdontologoEntradaDto odontologoEntrada){
        return  modelMapper.map(odontologoEntrada, Odontologo.class);
    }

    private OdontologoSalidaDto entityToDto(Odontologo odontologoSalida){
        return modelMapper.map(odontologoSalida, OdontologoSalidaDto.class);
    }

    private Odontologo odontologoModificadoEntradaDtoToEntity(OdontologoModificacionEntradaDto OdontologoModificado){
        return modelMapper.map(OdontologoModificado, Odontologo.class);
    }

}
