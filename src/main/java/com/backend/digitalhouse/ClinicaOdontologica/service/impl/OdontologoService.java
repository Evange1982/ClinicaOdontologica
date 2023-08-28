package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dao.IDao;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Odontologo;
import com.backend.digitalhouse.ClinicaOdontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final IDao<Odontologo> odontologoIDao;
    private final ModelMapper modelMapper;

    public OdontologoService(IDao<Odontologo> OdontologoIDao, ModelMapper modelMapper){
        this.odontologoIDao = OdontologoIDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        List<Odontologo> odontologos = odontologoIDao.listarTodos();
        return odontologos;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        Odontologo odontologoRegistrado = odontologoIDao.registrar(odontologo);
        return odontologoRegistrado;
    }

    @Override
    public Odontologo buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }

    @Override
    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);

    }

}
