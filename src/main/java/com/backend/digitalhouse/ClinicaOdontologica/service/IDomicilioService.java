package com.backend.digitalhouse.ClinicaOdontologica.service;

import com.backend.digitalhouse.ClinicaOdontologica.entity.Domicilio;
import java.util.List;

public interface IDomicilioService {

    List<Domicilio> listarDomicilios();

    Domicilio registrarDomicilio(Domicilio domicilio);

    Domicilio buscarDomicilioPorId(int id);

    Domicilio modificarDomicilio(Domicilio domicilio);

    void eliminarDomicilio(int id);

}
