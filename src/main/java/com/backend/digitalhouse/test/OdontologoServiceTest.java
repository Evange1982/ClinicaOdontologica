package com.backend.digitalhouse.test;


import com.backend.digitalhouse.dao.impl.OdontologoDaoH2;
import com.backend.digitalhouse.entity.Odontologo;
import com.backend.digitalhouse.service.OdontologoService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class OdontologoServiceTest {

    private final OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());


    @Test
    public void deberiaAgregarUnOdontologo() {
        Odontologo odontologo = new Odontologo("12345", "Patricia", "Damiani");

        Odontologo odontologoTest = odontologoService.guardarOdontologo(odontologo);

        assertTrue(odontologoTest.getId() != 0);

    }

    @Test
    public void listarTodosLosOdontologos() {
        List<Odontologo> odontologoList = odontologoService.listarOdontologos();
        assertFalse(odontologoList.isEmpty());

    }
}