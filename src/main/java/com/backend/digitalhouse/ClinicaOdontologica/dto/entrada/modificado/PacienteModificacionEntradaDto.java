package com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteModificacionEntradaDto {

    @NotNull
    private int id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private String dni;

    @NotNull
    private LocalDateTime fechaIngreso;

    @NotNull
    private DomicilioModificacionEntradaDto domicilio;

    public PacienteModificacionEntradaDto() {
    }

    public PacienteModificacionEntradaDto(int id, String nombre, String apellido, String dni, LocalDateTime fechaIngreso, DomicilioModificacionEntradaDto domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public PacienteModificacionEntradaDto(String nombre, String apellido, String dni, LocalDateTime fechaIngreso, DomicilioModificacionEntradaDto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioModificacionEntradaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioModificacionEntradaDto domicilio) {
        this.domicilio = domicilio;
    }
}