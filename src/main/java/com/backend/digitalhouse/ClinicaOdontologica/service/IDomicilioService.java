package com.backend.digitalhouse.ClinicaOdontologica.service;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.domicilio.DomicilioEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.DomicilioModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.domicilio.DomicilioSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Domicilio;
import java.util.List;

public interface IDomicilioService {

    List<DomicilioSalidaDto> listarDomicilios();

    DomicilioSalidaDto registrarDomicilio(DomicilioEntradaDto domicilio);

    DomicilioSalidaDto buscarDomicilioPorId(int id);

    DomicilioSalidaDto modificarDomicilio(DomicilioModificacionEntradaDto domicilioEntrada);

    void eliminarDomicilio(int id);

}
