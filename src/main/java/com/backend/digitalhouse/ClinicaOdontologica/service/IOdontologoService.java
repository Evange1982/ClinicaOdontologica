package com.backend.digitalhouse.ClinicaOdontologica.service;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Odontologo;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    List<OdontologoSalidaDto> listarOdontologos();

    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

   OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException;

}
