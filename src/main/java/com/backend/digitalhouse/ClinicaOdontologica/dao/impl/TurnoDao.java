package com.backend.digitalhouse.ClinicaOdontologica.dao.impl;


import com.backend.digitalhouse.ClinicaOdontologica.dao.IDao;
import com.backend.digitalhouse.ClinicaOdontologica.entity.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoDao implements IDao<Turno> {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoDao.class);


    private List<Turno> turnos;

    public TurnoDao() {
        turnos = new ArrayList<>();
    }

    @Override
    public Turno registrar(Turno turno) {
        Turno turnoGuardado = turno;

        turnos.add(turnoGuardado);
        LOGGER.info("Turno guardado: {}", turno);

        return turnoGuardado;
    }

    @Override
    public List<Turno> listarTodos() {
        LOGGER.info("Listado de todos los turnos: {}", turnos);
        return turnos;
    }

    @Override
    public Turno buscarPorId(int id) {
        Turno turnoBuscado = null;
        turnoBuscado = turnos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        LOGGER.info("Turno encontrado: {}", turnoBuscado);
        return turnoBuscado;
    }
    @Override
    public void eliminar(int id) {
        Turno t = buscarPorId(id);
        if (t != null) {
            turnos.remove(t);
            LOGGER.info("Se ha eliminado el turno con id: {}", id);
        } else {
            LOGGER.error("No existe un turno con ese id");
        }

    }


    @Override
    public Turno modificar(Turno turno) {
        eliminar(turno.getId());
        registrar(turno);
        LOGGER.info("Turno actualizado, nuevos datos: {}", turno);
        return turno;
    }
}
