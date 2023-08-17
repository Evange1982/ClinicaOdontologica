package com.backend.digitalhouse.test;

import com.backend.digitalhouse.dao.impl.PacienteDaoH2;
import com.backend.digitalhouse.entity.Domicilio;
import com.backend.digitalhouse.entity.Paciente;
import com.backend.digitalhouse.service.PacienteService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {
    private PacienteService pacienteService = new PacienteService(new PacienteDaoH2());



    @Test
    public void deberiaAgregarUnPaciente() {
        Paciente pacTest = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), new Domicilio("Calle", 13, "Localidad", "Provincia"));

        Paciente pacienteResult = pacienteService.registrarPaciente(pacTest);

        assertEquals(3, pacienteResult.getId());
        assertEquals(123456, pacienteResult.getDni());

    }

    @Test
    public void deberiaEncontrarElPacienteConId2() {
        Paciente paciente = pacienteService.buscarPacientePorId(2);
        assertNotNull(paciente);
        assertEquals("Clemente", paciente.getApellido());
    }

    @Test
    public void deberiaEliminarElPacienteConId3() {
        pacienteService.eliminarPaciente(3);
        assertNull(pacienteService.buscarPacientePorId(3));
    }

    @Test
    public void listarTodosLosPacientes() {
        List<Paciente> pacientesTest = pacienteService.listarPacientes();
        assertFalse(pacientesTest.isEmpty());
        assertTrue(pacientesTest.size() >= 2);

    }


}