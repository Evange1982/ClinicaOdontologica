package com.backend.digitalhouse.ClinicaOdontologica;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.domicilio.DomicilioEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.ClinicaOdontologica.service.impl.PacienteService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PacienteServiceTest {

	@Autowired
	private PacienteService pacienteService;


	@Test
	@Order(1)
	void deberiaInsertarUnPacienteDeNombreJuanConId1(){
		PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 111111, LocalDate.of(2023, 12, 9), new DomicilioEntradaDto("calle", 1232, "localidad", "provincia"));

		PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

		assertEquals("Juan", pacienteSalidaDto.getNombre());
		assertEquals(1, pacienteSalidaDto.getId());
		LocalDateTime.now();
	}

	@Test
	@Order(2)
	void deberiaRetornarseUnaListaNoVaciaDePacientes(){

		assertTrue(pacienteService.listarPacientes().size() > 0);
	}

	@Test
	@Order(4)
	void alIntentarActualizarElPacienteId2_deberiaLanzarseUnaResourceNotFoundException(){
		PacienteModificacionEntradaDto pacienteModificacionEntradaDto = new PacienteModificacionEntradaDto();
		pacienteModificacionEntradaDto.setId(2L);
		assertThrows(ResourceNotFoundException.class, () -> pacienteService.modificarPaciente(pacienteModificacionEntradaDto));
	}

	@Test
	@Order(3)
	void alIntentarEliminarUnPacienteYaEliminado_deberiaLanzarseUnResourceNotFoundException(){
		try{
			pacienteService.eliminarPaciente(1L);
		} catch (Exception e){
			e.printStackTrace();
		}
		assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
	}

}
