package com.backend.digitalhouse.ClinicaOdontologica;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.domicilio.DomicilioEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.ClinicaOdontologica.service.impl.OdontologoService;
import com.backend.digitalhouse.ClinicaOdontologica.service.impl.PacienteService;
import com.backend.digitalhouse.ClinicaOdontologica.service.impl.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    @Test
    void verificarQueSeGeneroTurno() throws BadRequestException {

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 111111, LocalDate.of(2023, 12, 9), new DomicilioEntradaDto("calle", 1232, "localidad", "provincia"));
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);


        OdontologoEntradaDto nuevoOdontologo = new OdontologoEntradaDto("AA-12345678", "Juan", "Mercado");
        OdontologoSalidaDto odontologoSalida = odontologoService.registrarOdontologo(nuevoOdontologo);


        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(pacienteSalidaDto.getId(), odontologoSalida.getId(), LocalDateTime.now());
        TurnoSalidaDto turnoRegistrado = turnoService.registrarTurno(turnoEntradaDto);


        assertNotNull(turnoRegistrado);

    }










        }
