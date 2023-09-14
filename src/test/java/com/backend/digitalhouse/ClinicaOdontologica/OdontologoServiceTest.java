package com.backend.digitalhouse.ClinicaOdontologica;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.OdontologoModificacionEntradaDto;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;

    @Test
    void registrarelOdontologoConMatriculaAA_12345678() throws BadRequestException {
        OdontologoEntradaDto nuevoOdontologo = new OdontologoEntradaDto("AA-12345678", "Juan", "Mercado");
        OdontologoSalidaDto odontologoSalida = odontologoService.registrarOdontologo(nuevoOdontologo);
        assertEquals("AA-12345678",odontologoSalida.getMatricula());

    }

    @Test
    void alTratarDeActaulizarUnOdontologoNoRegistradoDebeLanzarLaExcepcionResourceNotFoundException() {
        OdontologoModificacionEntradaDto odontologoModificacionEntradaDto = new OdontologoModificacionEntradaDto();
        odontologoModificacionEntradaDto.setId(222);
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.actualizarOdontologo(odontologoModificacionEntradaDto));

    }

    @Test
    void verificarQueHayaUnOdontologoRegistrado(){
    List<OdontologoSalidaDto> ListaDeOdontologo = odontologoService.listarOdontologos();
        assertTrue(ListaDeOdontologo.size()== 1);
    }

    @Test
    void buscarOdontologoNoExistentePorId() {
        Long idNoExistente = 111L; // ID que no existe
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(idNoExistente);
        assertNull(odontologoSalidaDto);
    }

    @Test
    void alIntentarEliminarUnOdontologoDeberiaLanzarseUnResourceNotFoundException(){
        try {
            odontologoService.eliminarOdontologo(1L);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }

}
