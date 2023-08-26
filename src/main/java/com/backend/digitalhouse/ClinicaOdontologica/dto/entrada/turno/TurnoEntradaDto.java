package com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.turno;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoEntradaDto {

    @NotNull(message = "El paciente no existe")
    private int pacienteId;

    @NotNull(message = "El odontologo no existe")
    private int odontologoId;

    //@JsonFormat( shape= "")
    @FutureOrPresent(message = "La fecha no puede ser anterior a dia de hoy")
    @NotNull(message = "Debe especificar la fecha y la hora")
    private LocalDateTime fechaYHora;


}
