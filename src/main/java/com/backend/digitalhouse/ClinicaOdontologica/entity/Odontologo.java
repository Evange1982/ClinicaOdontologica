package com.backend.digitalhouse.ClinicaOdontologica.entity;

import javax.persistence.*;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "ODONTOLOGOS_ID")
    private Long id;
    @Column(name = "ODONTOLOGOS_MATRICULA", nullable = false)
    private int matricula;
    @Column(name = "ODONTOLOGOS_NOMBRE", nullable = false, length = 25)
    private String nombre;
    @Column(name = "ODONTOLOGOS_APELLIDO", nullable = false, length = 50)
    private String apellido;

    public Odontologo(){}


    public Odontologo(int matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
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

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - Matricula: " + matricula;
    }
}
