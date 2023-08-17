package com.backend.digitalhouse.service;

import com.backend.digitalhouse.dao.IDao;
import com.backend.digitalhouse.entity.Paciente;

import java.util.List;

public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente registrarPaciente(Paciente paciente){
        return pacienteIDao.registrar(paciente);
    }

    public Paciente buscarPacientePorId(int id){
        return pacienteIDao.buscarPorId(id);
    }

    public List<Paciente> listarPacientes(){
        return pacienteIDao.listarTodos();
    }

    public void eliminarPaciente(int id){
        pacienteIDao.eliminar(id);
    }
}
