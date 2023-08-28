package com.backend.digitalhouse.ClinicaOdontologica.service;

import com.backend.digitalhouse.ClinicaOdontologica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    List<Odontologo> listarOdontologos();

    Odontologo registrarOdontologo(Odontologo odontologo);

    Odontologo buscarOdontologoPorId(int id);

    void eliminarOdontologo(int id);


}
