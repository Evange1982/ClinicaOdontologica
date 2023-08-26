package com.backend.digitalhouse.ClinicaOdontologica.service.impl;

import com.backend.digitalhouse.ClinicaOdontologica.dao.IDao;
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
    public List<Domicilio> listarDomicilios() {
        List<Domicilio> domicilios = domicilioIDao.listarTodos();
        return domicilios;
    }

    @Override
    public Domicilio registrarDomicilio(Domicilio domicilio) {
        Domicilio domicilioRegistrado = domicilioIDao.registrar(domicilio);
        return domicilioRegistrado;
    }

    @Override
    public Domicilio buscarDomicilioPorId(int id) {
        return domicilioIDao.buscarPorId(id);
    }

    @Override
    public Domicilio modificarDomicilio(Domicilio domicilio) {
        Domicilio domiciliomodificado = domicilioIDao.modificar(domicilio);
        return null;
    }

    @Override
    public void eliminarDomicilio(int id) {
        domicilioIDao.eliminar(id);
    }
}
