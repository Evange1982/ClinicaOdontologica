package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dao.IDao;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Odontologo;
import com.backend.digitalhouse.ClinicaOdontologica.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoIDao.registrar(odontologo);
    }

    @Override
    public Odontologo buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoIDao.listarTodos();
    }

    @Override
    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);
    }
}
