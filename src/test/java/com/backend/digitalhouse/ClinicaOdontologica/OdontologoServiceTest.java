package com.backend.digitalhouse.ClinicaOdontologica;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.ClinicaOdontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;

    @Test
    @Order(1)
    void registrarelOdontologoConMatriculaAA_12345678() throws BadRequestException {
        OdontologoEntradaDto nuevoOdontologo = new OdontologoEntradaDto("AA-12345678", "Juan", "Mercado");
        OdontologoSalidaDto odontologoSalida = odontologoService.registrarOdontologo(nuevoOdontologo);
        assertEquals(nuevoOdontologo.getMatricula(), odontologoSalida.getMatricula());
    }

/*
    @Test
    @Order(2)
    void alTratarDeRegistrarUnOdontologoExistenteDebeLanzarLaExcepcionResourceNotFoundException(){
        OdontologoEntradaDto nuevoOdontologo = new OdontologoEntradaDto("AA-12345678", "Juan", "Mercado");
        assertThrows(BadRequestException.class, () -> odontologoService.registrarOdontologo(nuevoOdontologo));
    }
*/


}
