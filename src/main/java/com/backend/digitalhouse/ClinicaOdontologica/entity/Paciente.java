package com.backend.digitalhouse.ClinicaOdontologica.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PACIENTES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"PACIENTES_DNI"})
})
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PACIENTES_ID")
    private Long id;
    @Column(name = "PACIENTES_NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "PACIENTES_APELLIDO", nullable = false, length = 50)
    private String apellido;
    @Column(name = "PACIENTES_DNI", nullable = false)
    private int dni;
    @Column(name = "PACIENTES_FECHA_INGRESO", nullable = false)
    private LocalDate fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DOMICILIOS_ID")
    private Domicilio domicilio;
    public Paciente(){
    }

    public Paciente(String nombre, String apellido, int dni, LocalDate fechaIngreso, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - DNI: " + dni + " - Fechas de ingreso: " + fechaIngreso + " - Domicilio: " + domicilio;
    }
}
